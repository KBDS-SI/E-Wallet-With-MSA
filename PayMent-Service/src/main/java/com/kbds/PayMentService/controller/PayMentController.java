package com.kbds.PayMentService.controller;

import com.kbds.PayMentService.dto.PayMentDto;
import com.kbds.PayMentService.jpa.PayMentEntity;
import com.kbds.PayMentService.service.PayMentService;
import com.kbds.PayMentService.vo.PayMentRequest;
import com.kbds.PayMentService.vo.PayMentResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment-service")
public class PayMentController {
    private Environment env;

    private PayMentService payMentService;

    public PayMentController(Environment env, PayMentService payMentService) {
        this.env = env;
        this.payMentService = payMentService;
    }

    @PostMapping("/createPayMent")
    public ResponseEntity<List<PayMentResponse>> createPayMent(@RequestBody PayMentRequest payMentRequest ) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PayMentDto payMentDto = mapper.map(payMentRequest, PayMentDto.class);
        payMentService.createPayMent(payMentDto);

        Iterable<PayMentEntity> payMentList = payMentService.getReceivePayList(payMentDto.getReceiveId());

        List<PayMentResponse> result = new ArrayList();
        payMentList.forEach(v -> {
//            v.setSendAt(LocalDateTime.parse(sdf.format(v.getSendAt())));
            log.info("TIMESTAMP : " + v.getSendAt());
            result.add(new ModelMapper().map(v, PayMentResponse.class));
        });


        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/receivePayMent")
    public String receivePayMent(@RequestBody PayMentRequest payMentRequest){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PayMentDto payMentDto = mapper.map(payMentRequest, PayMentDto.class);
        String returnString = payMentService.receivePayMent(payMentDto);
        return returnString;
    }
}
