package com.kbds.chargehistoryservice.service;

import com.example.ewallet.vo.ResponseEwallet;
import com.kbds.chargehistoryservice.client.EwalletServiceClient;
import com.kbds.chargehistoryservice.client.RemitServiceClient;
import com.kbds.chargehistoryservice.dto.ChargeHistoryDto;
import com.kbds.chargehistoryservice.jpa.ChargeHistoryEntity;
import com.kbds.chargehistoryservice.jpa.ChargeHistoryRepository;
import com.kbds.remit.vo.RequestRemit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@Slf4j
@Service
public class ChargeHistoryServiceImp implements ChargeHistoryService{

    ChargeHistoryRepository chargeHistoryRepository;

    RemitServiceClient remitServiceClient;

    EwalletServiceClient ewalletServiceClient;

    @Autowired
    public ChargeHistoryServiceImp(ChargeHistoryRepository chargeHistoryRepository, RemitServiceClient remitServiceClient, EwalletServiceClient ewalletServiceClient) {
        this.chargeHistoryRepository = chargeHistoryRepository;
        this.remitServiceClient = remitServiceClient;
        this.ewalletServiceClient = ewalletServiceClient;
    }

    @Override
    public Iterable<ChargeHistoryEntity> getChargeHistoryTop5(String userId) {
        return chargeHistoryRepository.findTop5ByUserIdOrderByTransactionTimeDesc(userId);
    }

    @Override
    public ChargeHistoryDto createChargeHistory(ChargeHistoryDto chargeHistoryDto) {
        ResponseEntity<ResponseEwallet> responseEwallet = ewalletServiceClient.getSearchEwallet(chargeHistoryDto.getUserId());
        chargeHistoryDto.setEwalletId(responseEwallet.getBody().getEwalletId());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ChargeHistoryEntity chargeHistoryEntity = mapper.map(chargeHistoryDto, ChargeHistoryEntity.class);
        chargeHistoryEntity.setFinalAmt(responseEwallet.getBody().getAmt().add(new BigDecimal(chargeHistoryEntity.getAmt().toString())));
        chargeHistoryRepository.save(chargeHistoryEntity);

        /* 충전 후 입출금 이력에도 INSERT 처리 */
        RequestRemit remit = new RequestRemit();

        remit.setUserId(chargeHistoryDto.getUserId());
        remit.setEwalletId(chargeHistoryDto.getEwalletId());
        remit.setRemitCode("1");
        remit.setAmt(chargeHistoryDto.getAmt());
        remit.setMemo("충전");
        remit.setOppoUserId(chargeHistoryDto.getUserId());
        remit.setCancelYn("0");
        remit.setFinBal(new BigDecimal("50000000"));

        ResponseEntity createRemitRes =  remitServiceClient.createRemit(remit);

        ChargeHistoryDto returnChargeHistoryDto = mapper.map(chargeHistoryEntity, ChargeHistoryDto.class);
        return returnChargeHistoryDto;
    }

    @Override
    public Iterable<ChargeHistoryEntity> getChargeHistory(String userId) {
        return chargeHistoryRepository.findByUserId(userId);
    }
}
