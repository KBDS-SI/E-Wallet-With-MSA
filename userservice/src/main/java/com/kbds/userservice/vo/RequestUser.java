package com.kbds.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message = "userId cannot be null")
    @Size(min = 5, max = 20, message = "userId not be less than 5 characters")
    private String userId;

    @NotNull(message = "username cannot be null")
    @Size(min = 2, message = "username not be less than two characters")
    private String username;

    @NotNull(message = "pwd cannot be null")
    @Size(min = 5, message = "pwd must be equal or grater than 8 characters")
    private String pwd;

    @NotNull(message = "phone cannot be null")
    @Size(min = 10, message = "phone must be equal or grater than 11 characters")
    private String phone;
}
