package com.jedis.ts;
import java.util.Set;

import redis.clients.jedis.Jedis;
public class JedisTS {

    public static void main(final String[] args) {
        final Jedis jedis = new Jedis("localhost");
//        System.out.println(jedis.ping());

       // 获取存储的数据并输出
//       System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));

   /*  //存储数据到列表中
       jedis.lpush("tutorial-list", "Redis");
       jedis.lpush("tutorial-list", "Mongodb");
       jedis.lpush("tutorial-list", "Mysql");*/
      // 获取存储的数据并输出
       /*   final List<String> list = jedis.lrange("tutorial-list", 0 ,5);
      for(int i=0; i<list.size(); i++) {
        System.out.println("Stored string in redis:: "+list.get(i));*/

       // 获取数据并输出
       final Set<String> list = jedis.keys("*");
       for(final String el : list){
           System.out.println(el);
       }
       final String channels = "mychannel";
       jedis.del("lu");
      /* jedis.subscribe(jedisPubSub, channels);*/
     }


}
