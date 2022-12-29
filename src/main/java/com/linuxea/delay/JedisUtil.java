package com.linuxea.delay;

import redis.clients.jedis.Jedis;

public class JedisUtil {

  public static Jedis getJedis(String host, int port) {
    return new Jedis(host, port);
  }

}
