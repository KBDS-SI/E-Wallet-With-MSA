package com.kbds.userservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "userId cannot be null")
    @Size(min = 5, message = "userId not be less than two characters")
    private String userId;

    @NotNull(message = "pwd cannot be null")
//    @Size(min = 8, message = "Password must be equals or grater than 8 characters")
    private String pwd;
}
