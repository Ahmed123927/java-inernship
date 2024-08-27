package com.example.elearningplatform.dto.mapping;

import com.example.elearningplatform.dto.UserDto;
import com.example.elearningplatform.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

    // Convert UserDto to User entity
    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .build();
    }
}
