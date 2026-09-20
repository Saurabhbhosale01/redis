package com.redis.config.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    //this method is used to get the data and return type is generic class
    public <t> t get(String key, Class<t> entityClass){
        try{
            Object obj = redisTemplate.opsForValue().get(key);
            log.info("objjjjj"+obj);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(obj.toString(),entityClass );
        }
        catch (Exception e){
            log.error("exception "+e);
            return null;
        }
    }


    /** this method is used to set the sate in redis the ttl is time to leave it tells for
     * how much time period the data will present in redis cache and
     * timeUnite fot the unite of time like sec, min
     */
    public void set(String key, Object o, long ttl){
        try{
            redisTemplate.opsForValue().set(key,o.toString(),ttl, TimeUnit.SECONDS);
        }
        catch (Exception e){
            log.error("exception"+e);
        }
    }
}
