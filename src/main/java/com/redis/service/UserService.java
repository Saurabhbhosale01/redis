package com.redis.service;

import com.redis.dto.UserDto;
import com.redis.entity.UserEntity;

public interface UserService {

    void addUser(UserDto userDto);

    UserDto getUserById(String id);

}
