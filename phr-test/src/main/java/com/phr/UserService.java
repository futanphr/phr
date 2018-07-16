package com.phr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
 
    @Cacheable(value = "my-redis-cache1", key ="#id", unless = "#result==null")
    public String getUser1(int id) {
        System.out.println("i am from userService getUser1");
        System.out.println(stringRedisTemplate.getValueSerializer());
        System.out.println(redisTemplate.getValueSerializer());
        return "fee";
    }
    @Cacheable(value = "my-redis-cache2", key ="#id", unless = "#result==null")
    public String getUser2(int id) {
        System.out.println("i am from userService getUser2");
        System.out.println(stringRedisTemplate.getValueSerializer());
        System.out.println(redisTemplate.getValueSerializer());
        return "fee";
    }


}
