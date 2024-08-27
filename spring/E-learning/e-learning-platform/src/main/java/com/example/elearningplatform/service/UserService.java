package com.example.elearningplatform.service;

import com.example.elearningplatform.dto.UserDto;
import com.example.elearningplatform.dto.mapping.UserMapper;
import com.example.elearningplatform.entity.User;
import com.example.elearningplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return UserMapper.toDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return UserMapper.toDto(user);
    }

    public void removeUserById(int id) {
        userRepository.deleteById(id);
    }

    public void update(UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(userDto.getId());
        existingUser.ifPresent(user -> {
            if (userDto.getFirstName() != null) {
                user.setFirstName(userDto.getFirstName());
            }
            if (userDto.getLastName() != null) {
                user.setLastName(userDto.getLastName());
            }
            if (userDto.getPhoneNumber() != null) {
                user.setPhoneNumber(userDto.getPhoneNumber());
            }
            if (userDto.getRole() != null) {
                user.setRole(userDto.getRole());
            }
            if (userDto.getEmail() != null) {
                user.setEmail(userDto.getEmail());
            }
            if (userDto.getAddress() != null) {
                user.setAddress(userDto.getAddress());
            }
            userRepository.save(user);
        });
    }

}
