package com.jedis.ts;

import redis.clients.jedis.JedisPubSub;


public class Subscriber extends JedisPubSub {
    public Subscriber() {
    }

    @Override
	public void onMessage(final String channel, final String message) {
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    @Override
	public void onSubscribe(final String channel, final int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }

    @Override
	public void onUnsubscribe(final String channel, final int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
