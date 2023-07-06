package com.kbds.userservice.service;

import com.kbds.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto userLogin(UserDto userDto);

    UserDto getUserDetailsByUserId(String userId);
    UserDto getUserByUserId(String userId);
}
