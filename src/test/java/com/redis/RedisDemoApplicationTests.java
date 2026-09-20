package com.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void sum(){
        redisTemplate.opsForValue().set("email","sau@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
    }

    @Test
    void shouldStoreAndRetrieveValueFromRedis() {

        redisTemplate.opsForValue().set("email", "sau@gmail.com");

        Object email = redisTemplate.opsForValue().get("email");

    }

}
