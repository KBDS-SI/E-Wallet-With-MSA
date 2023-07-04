package com.kbds.userservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String userId;
    private String pwd;
    private String encryptedPwd;
    private String username;
    private String phone;
    private LocalDateTime lastAccessTime;
}
