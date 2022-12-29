package com.linuxea.delay;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

public abstract class RedisDelayQueue<T extends DelayElement> implements DelayQueue<T>, Runnable {

  private final Jedis jedis;
  private final String queueName;
  private final AtomicBoolean running = new AtomicBoolean(true);
  private final Class<T> tClass;


  public RedisDelayQueue(Jedis jedis, String queueName, Class<T> tClass) {
    this.jedis = jedis;
    this.queueName = queueName;
    this.tClass = tClass;
    new Thread(this).start();
  }


  @Override
  public void add(T t, TimeUnit delayTimeUnit, Long delayValue) {
    String jsonString = JSONObject.toJSONString(t);
    long delayMillis = delayTimeUnit.toMillis(delayValue);
    jedis.zadd(this.queueName, System.currentTimeMillis() + delayMillis * 1.0, jsonString);
  }


  @Override
  public void shutdown() {
    this.running.set(Boolean.FALSE);
  }

  @Override
  public void run() {
    while (running.get()) {
      String lockKey = this.queueName + "lock";
      if (this.jedis.setnx(lockKey, "1") == 1) {
        try {
          doCore();
        } finally {
          this.jedis.del(lockKey);
        }
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }
  }

  private void doCore() {
    List<Tuple> tuples = jedis.zrangeByScoreWithScores(this.queueName, 0, Double.MAX_VALUE, 0, 100);
    Consumer<Tuple> consumer = tuple -> {
      double score = tuple.getScore();
      if (score <= System.currentTimeMillis()) {
        String element = tuple.getElement();
        T t = JSONObject.parseObject(element, tClass);
        this.callback().execute(t);
        this.jedis.zrem(this.queueName, element);
      }
    };

    tuples.forEach(consumer);
  }
}
