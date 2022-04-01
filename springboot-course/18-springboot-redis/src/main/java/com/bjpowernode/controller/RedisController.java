package com.bjpowernode.controller;

import com.bjpowernode.vo.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/26 11:36
 */

@RestController
public class RedisController {

    /**
     * RedisTemplate
     * 泛型 RedisTemplate<String,String>
     * 泛型 RedisTemplate<Object,Object>
     * RedisTemplate 啥也不写
     */
    @Resource
    private RedisTemplate redisTemplate;  //默认是jdk的序列化

    @Resource
    private StringRedisTemplate stringRedisTemplate; //String的序列化

    //添加数据到redis
    @PostMapping("/redis/addString")
    public String addRedis(String name, String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(name, value);
        return "向redis添加数据";
    }


    //从redis获取数据
    @GetMapping("/redis/getK")
    public String getK(String key){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        return "key="+ key + ",value=" +value;
    }

    @PostMapping("/redis/{k}/{v}")
    public String addStringKV(@PathVariable String k,
                              @PathVariable String v){

        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(k,v);
        return "向redis添加String数据";
    }

    @GetMapping("/redis/getStr/{key}")
    public String getStr(@PathVariable String key){
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        return "key="+ key + ",value=" +value;
    }

    /**
     * 设置RedisTemplate序列化
     * 可以设置key的序列化 可以设置value的序列化
     * 可以同时设置k，v序列化
     */
    @PostMapping("/redis/add")
    public String addString(String k ,String v){
        //设置key序列化
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        //设置value序列化
        redisTemplate.setValueSerializer( new StringRedisSerializer());

        redisTemplate.opsForValue().set(k,v);
        return "序列化后添加数据";
    }

    /**
     * 使用json序列化 把java对象转为json存储
     */
    @PostMapping("/redis/addJson")
    public String addJson(){
        Student student = new Student();
        student.setName("zs");
        student.setAge(22);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Student.class));
        redisTemplate.opsForValue().set("mystudent",student);
        return "java对象转为json存储到redis";
    }

    /**
     * 反序列化
     */
    @PostMapping("/redis/getJson")
    public String getJson(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Student.class));
        Object obj = redisTemplate.opsForValue().get("mystudent");
        return "反序列化:" + obj.toString();
    }
}
