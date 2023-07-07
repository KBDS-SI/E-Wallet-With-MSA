package com.example.ewallet.service;

import com.example.ewallet.dto.EwalletDto;
import com.example.ewallet.jpa.EwalletEntity;
import com.example.ewallet.jpa.EwalletID;
import com.example.ewallet.jpa.EwalletRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

    @Autowired
    private EntityManager em;

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
    @Transactional
    public EwalletDto updateBalance(EwalletDto ewalletDto) {
        EwalletEntity ewalletEntity = ewalletRepository.findByUserId(ewalletDto.getUserId());
        log.info("ewalletEntity : " + ewalletEntity.toString());
        BigDecimal newAmt = new BigDecimal("0");
        newAmt.add(ewalletEntity.getAmt());
        log.info("newAmt : " + newAmt.toString());

        EwalletEntity ewalletEntity1 = em.find(EwalletEntity.class, ewalletDto.getEwalletId());
        log.info("ewalletEntity1 : " + ewalletEntity1.toString());

        ewalletEntity1.setAmt(ewalletEntity1.getAmt().add(ewalletDto.getAmt()));
        log.info("ewalletEntity1,2 : " + ewalletEntity1.toString());

        return ewalletDto;
    }
}
