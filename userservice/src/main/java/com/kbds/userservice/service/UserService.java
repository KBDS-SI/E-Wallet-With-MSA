package com.kbds.userservice.service;

import com.kbds.userservice.dto.UserDto;

public interface UserService{
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
}
