package com.kbds.userservice.controller;

import com.kbds.userservice.dto.UserDto;
import com.kbds.userservice.service.UserService;
import com.kbds.userservice.vo.RequestUser;
import com.kbds.userservice.vo.ResponseUser;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseUser> join(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestUser, UserDto.class);
        UserDto user = userService.createUser(userDto);
        log.info("=============="+userDto);

//        try {
//            String apiUrl = "http://192.168.61.252:8000/ewallet-service/createEwallet";
//
//            URL url = new URL(apiUrl);
//
//            String postData = "{\"userId\":\"testId\""
//                              + ",\"ewalletId\":\"123123\""
//                              + ",\"amt\":\"0\""
//                              + "}";
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setDoOutput(true);
//
//            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
//            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//
//            OutputStream outputStream = conn.getOutputStream();
//            outputStream.write(postDataBytes);
//            outputStream.flush();
//            outputStream.close();
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//
//                in.close();
//
//            } else {
//                log.info("API 호출이 실패하였습니다. 응답코드 : " + responseCode);
//            }
//            conn.disconnect();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseUser> userLogin(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userDto = userService.userLogin(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/zz")
    public String zz() {
        return "zz";
    }

    @GetMapping("/health-check")
    public String healthcheck() {
        return "인증없이 들어오면 안되는데...";
    }
}