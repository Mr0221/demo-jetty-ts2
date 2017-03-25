package com.jedis.ts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher {
    private final JedisPool jedisPool;

    public Publisher(final JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void start() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("redisChat", line);
                } else {
                    break;
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
         }
    }
}
