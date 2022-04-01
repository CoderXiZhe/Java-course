package com.bjpowernode.redis;


import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class JedisTest {
    public static void main(String[] args) {
        /*连接redis*/
        Jedis jedis = new Jedis("192.168.116.128", 6379);
        Set<String> keys = jedis.keys("*");//keys *
        for (String key : keys) {
            System.out.println(key);
        }
        String set1 = jedis.srandmember("set1");
        System.out.println(set1);

        Set<String> set11 = jedis.smembers("set1");
        for (String key : set11) {
            System.out.println(key);
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", "1001");
        map.put("name", "zs");
        map.put("age", "23");
        Long stu1001 = jedis.hset("stu1001", map);
        System.out.println(stu1001);
        Long k1 = jedis.ttl("k1");
        System.out.println(k1);
    }
}
