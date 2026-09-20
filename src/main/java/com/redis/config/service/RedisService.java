package com.redis.config.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    @Value("${redis.cache.ttl}")
    private long ttl;


    /** this method is used to get the data from the redis and return type is generic class
     */
    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object value = redisTemplate.opsForValue().get(key);

            if (value == null) {
                log.info("Redis cache miss for key: {}", key);
                return null;
            }

            log.info("Redis cache hit for key: {}", key);

            return objectMapper.readValue(value.toString(), entityClass);

        } catch (Exception e) {
            log.error("Failed to get Redis key: {}", key, e);
            return null;
        }
    }


    /** this method is used to set the sate in redis the ttl is time to leave it tells for
     * how much time period the data will present in redis cache and
     * timeUnite fot the unite of time like sec, min
     */
    public void set(String key, Object value) {
        try {
            String json = objectMapper.writeValueAsString(value);

            redisTemplate.opsForValue().set(key, json, ttl, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Failed to set Redis key: {}", key, e);
        }
    }
}
