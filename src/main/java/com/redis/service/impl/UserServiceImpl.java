package com.redis.service.impl;

import com.redis.config.service.RedisService;
import com.redis.dto.UserDto;
import com.redis.entity.UserEntity;
import com.redis.mapper.ModelMapper;
import com.redis.repository.UserRepository;
import com.redis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RedisService redisService;
    private final ModelMapper modelMapper;

    @Override
    public void addUser(UserDto userDto) {

        UserEntity entity = modelMapper.toEntity(userDto);

        UserEntity saved = repository.save(entity);

        redisService.set(
                String.valueOf(saved.getId()),
                saved
        );
    }

    @Override
    public UserDto getUserById(String id) {

        // 1. Check Redis data is present
        UserEntity cachedData = redisService.get(id, UserEntity.class);
        log.info("redis cached data "+ cachedData);

        // 2. Cache HIT
        if (cachedData != null) {
            return modelMapper.toDto(cachedData);
        }

        // 3. Cache MISS → check database
        UserEntity entity = repository.findById(Integer.valueOf(id))
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        // 4. Store DB data in Redis
        redisService.set(
                id,
                entity
        );

        // 5. Return response
        return modelMapper.toDto(entity);
    }
}