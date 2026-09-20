package com.redis.mapper;

import com.redis.dto.UserDto;
import com.redis.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public UserEntity toEntity(UserDto userdto){
        return UserEntity.builder()
                .name(userdto.getName())
                .gmail(userdto.getGmail())
                .build();
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .gmail(userEntity.getGmail())
                .build();
    }

    public void updateEntity(UserDto userDto, UserEntity userEntity) {
        userEntity.setName(userDto.getName());
        userEntity.setGmail(userDto.getGmail());
    }
}
