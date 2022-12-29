package com.linuxea.delay;

public abstract class DefaultRedisDelayQueue<T extends DelayElement> extends RedisDelayQueue<T> {

  public DefaultRedisDelayQueue(String queueName, Class<T> tClass) {
    super(JedisUtil.getJedis(System.getenv("redisHost"), 6379), queueName, tClass);
  }
}
