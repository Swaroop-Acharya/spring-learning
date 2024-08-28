package com.example.curd.mapper;

import com.example.curd.dto.UserDto;
import com.example.curd.entity.User;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getName(), user.getEmail());
    }
}
