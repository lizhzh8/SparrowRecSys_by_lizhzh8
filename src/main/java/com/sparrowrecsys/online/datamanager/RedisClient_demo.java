package com.sparrowrecsys.online.datamanager;

import redis.clients.jedis.Jedis;

public class RedisClient_demo {
    //singleton Jedis
    private static volatile Jedis redisClient;
    final static String REDIS_END_POINT = "localhost";
    final static int REDIS_PORT = 6379;

    private RedisClient_demo() {
        redisClient = new Jedis(REDIS_END_POINT, REDIS_PORT);
    }

    public static Jedis getInstance() {
        if (null == redisClient) {
            synchronized (RedisClient_demo.class) {
                if (null == redisClient) {
                    redisClient = new Jedis(REDIS_END_POINT, REDIS_PORT);
                }
            }
        }
        return redisClient;
    }

}
