package com.example.remit.controller;

import com.example.remit.dto.RemitDto;
import com.example.remit.service.RemitService;
import com.example.remit.vo.RequestRemit;
import com.netflix.discovery.converters.Auto;
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
        remitService.createRemit(remitDto);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    @GetMapping("/welcome")
    public String welcome(){
        log.info("welcome to remit");
        return "welcome to remit";
    }
}
