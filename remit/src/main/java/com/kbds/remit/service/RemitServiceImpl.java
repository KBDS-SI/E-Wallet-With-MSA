package com.kbds.remit.service;

import com.kbds.remit.dto.RemitDto;
import com.kbds.remit.jpa.RemitEntity;
import com.kbds.remit.jpa.RemitRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Slf4j
@Service
public class RemitServiceImpl implements RemitService{
    RemitRepository remitRepository;
    @Autowired
    public RemitServiceImpl(RemitRepository remitRepository) {
        this.remitRepository = remitRepository;
    }

    @Override
    public RemitDto createRemit(RemitDto remitDto) {

        remitDto.setEwalletId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RemitEntity remitEntity = mapper.map(remitDto, RemitEntity.class);
        log.info("remitEntity : " + remitEntity.toString());
        remitRepository.save(remitEntity);
        return mapper.map(remitEntity, RemitDto.class);

    }
}
