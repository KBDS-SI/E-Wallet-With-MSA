package com.example.remit.service;

import com.example.remit.dto.RemitDto;
import com.example.remit.jpa.RemitEntity;
import com.example.remit.jpa.RemitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class RemitServiceImpl implements RemitService{

    @Autowired
    RemitRepository remitRepository;
    @Override
    public RemitDto createRemit(RemitDto remitDto) {

        remitDto.setEwalletId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RemitEntity remitEntity = mapper.map(remitDto, RemitEntity.class);
        remitRepository.save(remitEntity);
        return null;

    }
}
