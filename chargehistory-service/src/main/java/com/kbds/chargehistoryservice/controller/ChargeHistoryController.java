package com.kbds.chargehistoryservice.controller;

import com.kbds.chargehistoryservice.dto.ChargeHistoryDto;
import com.kbds.chargehistoryservice.service.ChargeHistoryService;
import com.kbds.chargehistoryservice.vo.ChargeHistoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@RequestMapping("/chargehistory-service")
public class ChargeHistoryController {

    private Environment env;

    private ChargeHistoryService chargeHistoryService;

    public ChargeHistoryController(Environment env, ChargeHistoryService chargeHistoryService) {
        this.env = env;
        this.chargeHistoryService = chargeHistoryService;
    }

    @PostMapping("/createChargeHistory")
    public String createChargeHistory(@RequestBody ChargeHistoryRequest chargeHistoryRequest){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ChargeHistoryDto chargeHistoryDto = mapper.map(chargeHistoryRequest, ChargeHistoryDto.class);
        chargeHistoryService.createChargeHistory(chargeHistoryDto);

        String returnString = "";

//        try {
//            String apiUrl = "http://192.168.61.190:8000/user-service/login";
//
//            URL url = new URL(apiUrl);
//
//            String postData = "{\"userId\":\"testId\""
//                              + ",\"username\":\"123123\""
//                              + ",\"pwd\":\"testPwd\""
//                              + ",\"phone\":\"01012345678\""
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

            //conn.setRequestMethod("GET");
            /* POST 방식 예제
            String postData = "param1=value1&param2=value2"; 일반String
            String postData = "{\"param1\":\"value1\",\"param2\":\"value2\"}"; JSON

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // POST 요청 바디 데이터 설정
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.getOutputStream().write(postDataBytes);

            JSON 타입일때
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postDataBytes);
            outputStream.flush();
            outputStream.close();
             */

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
//                returnString = response.toString();
//            } else {
//                returnString = "API 호출이 실패하였습니다. 응답코드 : " + responseCode;
//            }
//
//            conn.disconnect();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

        returnString =  "ChargeHistory Create OK!";
        return returnString;
    }
}
