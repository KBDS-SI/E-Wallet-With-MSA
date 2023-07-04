package com.kbds.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String userId;
    private String pwd;
    private String username;
    private String phone;
    private LocalDateTime lastAccessTime;
}
