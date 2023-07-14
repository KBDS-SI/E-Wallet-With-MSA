package com.kbds.userservice.controller;

import com.kbds.userservice.dto.UserDto;
import com.kbds.userservice.service.UserService;
import com.kbds.userservice.vo.RequestLogin;
import com.kbds.userservice.vo.RequestUser;
import com.kbds.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    private UserService userService;
    private Environment env;


    @Autowired
    public UserController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseUser> join(@RequestBody RequestUser requestUser) {
        log.info("================== join 호출");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestUser, UserDto.class);
        UserDto user = userService.createUser(userDto);
        log.info("================== userDto : "+userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseUser> userLogin(@RequestBody RequestLogin requestLogin) {
//        UserDetails userDetails = userService.loadUserByUsername(requestUser);
        log.info("================== userLogin 호출");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestLogin, UserDto.class);
        userDto = userService.userLogin(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/hello")
    public String hello() {
        return String.format("It's Working in User Service"
                + ",port(local.server.port)=" + env.getProperty("local.server.port")+"\n"
                + ",port(server.port)=" + env.getProperty("server.port")+"\n"
                + ",token secret=" + env.getProperty("token.secret")+"\n"
                + ",token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/ss")
    public String ss() {
        return "ㄴㄴ";
    }

    @GetMapping("/dd")
    public String dd() {
        return "ㅇㅇ";
    }

    @GetMapping("/health-check")
    public String healthcheck() {
        return String.format("It's Working in User Service"
        + ",port(local.server.port)=" + env.getProperty("local.server.port")+"\n"
        + ",port(server.port)=" + env.getProperty("server.port")+"\n"
        + ",token secret=" + env.getProperty("token.secret")+"\n"
        + ",token expiration time=" + env.getProperty("token.expiration_time"));
    }
}