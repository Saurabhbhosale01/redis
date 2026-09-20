package com.redis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto{

    private Integer id;

    private String name;

    private String gmail;

}
