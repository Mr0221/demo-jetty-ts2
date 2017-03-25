package com.jedis.ts;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class SubThread extends Thread {
    private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();

    private final String channel = "mychannel";

    public SubThread(final JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(subscriber, channel);
        } catch (final Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
