package com.jedis.ts;

import redis.clients.jedis.JedisPool;


public class PubSubDemo
{
    public static void main( final String[] args )
    {
        // 替换成你的reids地址和端口
        final String redisIp = "localhost";
        final int reidsPort = 6379;
        final JedisPool jedisPool = new JedisPool(redisIp, reidsPort);
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", redisIp, reidsPort));

        final SubThread subThread = new SubThread(jedisPool);
        subThread.start();

        final Publisher publisher = new Publisher(jedisPool);
        publisher.start();
    }
}
