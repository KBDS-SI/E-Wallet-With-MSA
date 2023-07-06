package com.example.ewallet.service;

import com.example.ewallet.dto.EwalletDto;
import com.example.ewallet.jpa.EwalletEntity;
import com.example.ewallet.jpa.EwalletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EwalletServiceImpl implements EwalletService {
    @Autowired
    EwalletRepository ewalletRepository;


    @Override
    public EwalletDto createEwallet(EwalletDto ewalletDto) {

        ewalletDto.setEwalletId(UUID.randomUUID().toString());
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
}
