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

        return "ChargeHistory Create OK!";
    }
}
