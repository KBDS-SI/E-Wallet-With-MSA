package com.kbds.chargehistoryservice.service;

import com.kbds.chargehistoryservice.dto.ChargeHistoryDto;
import com.kbds.chargehistoryservice.jpa.ChargeHistoryEntity;
import com.kbds.chargehistoryservice.jpa.ChargeHistoryRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@Slf4j
@Service
public class ChargeHistoryServiceImp implements ChargeHistoryService{

    ChargeHistoryRepository chargeHistoryRepository;

    @Autowired
    public ChargeHistoryServiceImp(ChargeHistoryRepository chargeHistoryRepository) {
        this.chargeHistoryRepository = chargeHistoryRepository;
    }

    @Override
    public ChargeHistoryDto createChargeHistory(ChargeHistoryDto chargeHistoryDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ChargeHistoryEntity chargeHistoryEntity = mapper.map(chargeHistoryDto, ChargeHistoryEntity.class);
        chargeHistoryEntity.setFinalAmt(new BigDecimal("0").add(new BigDecimal(chargeHistoryEntity.getAmt().toString())));
        chargeHistoryRepository.save(chargeHistoryEntity);

        ChargeHistoryDto returnChargeHistoryDto = mapper.map(chargeHistoryEntity, ChargeHistoryDto.class);
        return returnChargeHistoryDto;
    }

    @Override
    public Iterable<ChargeHistoryEntity> getChargeHistory(String userId) {
        return chargeHistoryRepository.findByUserId(userId);
    }
}
