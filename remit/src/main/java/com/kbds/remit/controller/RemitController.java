package com.kbds.remit.controller;

import com.kbds.remit.dto.RemitDto;
import com.kbds.remit.jpa.RemitEntity;
import com.kbds.remit.service.RemitService;
import com.kbds.remit.vo.RequestRemit;
import com.kbds.remit.vo.ResponseRemit;
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
import java.util.ArrayList;
import java.util.List;

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

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PostMapping("/remitHistory")
    public ResponseEntity<List<ResponseRemit>> getRemitHistory(@RequestBody RequestRemit requestRemit){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RemitDto remitDto = mapper.map(requestRemit, RemitDto.class);

        Iterable<RemitEntity> remitList = remitService.getRemitByAll(remitDto);

        List<ResponseRemit> result = new ArrayList();
        remitList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseRemit.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/payMentToRemit")
    public ResponseEntity createRemitFromPayMent(@RequestBody RequestRemit remit){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RemitDto remitDto = mapper.map(remit, RemitDto.class);
        remitDto = remitService.createRemitFromPayMent(remitDto);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    @GetMapping("/welcome")
    public String welcome(){
        log.info("welcome to remit");
        return "welcome to remit";
    }
}
