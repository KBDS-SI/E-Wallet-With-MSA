package com.example.ewallet.service;

import com.example.ewallet.dto.EwalletDto;
import com.example.ewallet.jpa.EwalletEntity;
import com.example.ewallet.jpa.EwalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
public class EwalletServiceImpl implements EwalletService {
    @Autowired
    EwalletRepository ewalletRepository;


    @Override
    public EwalletDto createEwallet(EwalletDto ewalletDto) {
        if ("".equals(ewalletDto.getEwalletId()) || ewalletDto.getEwalletId() == null) {
            ewalletDto.setEwalletId(UUID.randomUUID().toString());
        }

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EwalletEntity ewalletEntity = mapper.map(ewalletDto, EwalletEntity.class);
        ewalletRepository.save(ewalletEntity);
        return null;
    }


    @Override
    public EwalletDto searchEwallet(String userId) {
        EwalletEntity ewalletEntity = ewalletRepository.findByUserId(userId);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EwalletDto ewalletDto = mapper.map(ewalletEntity, EwalletDto.class);

        return ewalletDto;
    }

    @Override
    public EwalletDto updateBalance(EwalletDto ewalletDto) {
        EwalletEntity ewalletEntity = ewalletRepository.findByUserId(ewalletDto.getUserId());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        EwalletDto ewalletDto1 = mapper.map(ewalletEntity, EwalletDto.class);
        BigDecimal finalBal = ewalletDto1.getAmt().add(ewalletDto.getAmt());
        ewalletDto1.setAmt(ewalletDto1.getAmt().add(ewalletDto.getAmt()));
        EwalletEntity ewalletEntity1 = mapper.map(ewalletDto1, EwalletEntity.class);
        ewalletRepository.save(ewalletEntity1);

        ewalletDto = mapper.map(ewalletEntity1, EwalletDto.class);
        return ewalletDto;
    }
}
