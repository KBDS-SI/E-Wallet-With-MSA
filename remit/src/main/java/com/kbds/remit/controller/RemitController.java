package com.kbds.remit.controller;

import com.kbds.remit.dto.RemitDto;
import com.kbds.remit.service.RemitService;
import com.kbds.remit.vo.RequestRemit;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
@RequestMapping("/remit")
@Slf4j
public class RemitController {
    private Environment env;
    private RemitService remitService;

    @Autowired
    public RemitController(Environment env, RemitService remitService){
        this.env = env;
        this.remitService = remitService;
    }


    @PostMapping("/remit")
    public ResponseEntity createRemit(@RequestBody RequestRemit remit){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RemitDto remitDto = mapper.map(remit, RemitDto.class);
        remitDto = remitService.createRemit(remitDto);

        if (remit.getRemitCode().equals("2")) { //remitCode - "1" : 입금, "2" : 출금
            try {
                String apiUrl = "http://192.168.61.190:8000/payment-service/createPayMent";
                URL url = new URL(apiUrl);
                String postData="{\"sendId\":\"" + remitDto.getUserId() + "\""
                               + ",\"receiveId\":\"" + remitDto.getOppoUserId() + "\""
                               + ",\"sendAmt\":\"" + remitDto.getAmt() + "\""
                               + ",\"ewalletId\":\"" + remitDto.getEwalletId() + "\""
                               + "}";
                log.info("postData = " + postData);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                byte[] posetDataBytes = postData.getBytes(StandardCharsets.UTF_8);
                conn.setRequestProperty("Content-Length", String.valueOf(posetDataBytes.length));

                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(posetDataBytes);
                outputStream.flush();
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader((new InputStreamReader(conn.getInputStream())));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    log.info("송금내역 정상 생성 : " + response.toString());
                } else {
                    log.info("API 호출 실패!! 응답코드 : " + responseCode);
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }
    @GetMapping("/welcome")
    public String welcome(){
        log.info("welcome to remit");
        return "welcome to remit";
    }
}
