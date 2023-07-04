package com.kbds.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data // 얘 없으면 ResponseBody에 안나옴
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String userId;
    private String pwd;
    private String username;
    private String phone;
    private LocalDateTime lastAccessTime;
}
