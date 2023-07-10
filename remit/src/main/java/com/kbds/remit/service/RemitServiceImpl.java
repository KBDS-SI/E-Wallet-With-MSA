package com.kbds.remit.service;

import com.kbds.remit.vo.PayMentRequest;
import com.kbds.remit.vo.PayMentResponse;
import com.kbds.remit.client.PayMentServiceClient;
import com.kbds.remit.dto.RemitDto;
import com.kbds.remit.jpa.RemitEntity;
import com.kbds.remit.jpa.RemitRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Data
@Slf4j
@Service
public class RemitServiceImpl implements RemitService{
    RemitRepository remitRepository;

    PayMentServiceClient payMentServiceClient;

    @Autowired
    public RemitServiceImpl(RemitRepository remitRepository, PayMentServiceClient payMentServiceClient) {
        this.remitRepository = remitRepository;
        this.payMentServiceClient = payMentServiceClient;
    }

    @Override
    public Iterable<RemitEntity> getRemitByAll(RemitDto remitDto) {
        return remitRepository.findByUserIdOrderByTransTimeDesc(remitDto.getUserId());
    }

    @Override
    public RemitDto createRemit(RemitDto remitDto) {

//        remitDto.setEwalletId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RemitEntity remitEntity = mapper.map(remitDto, RemitEntity.class);
        log.info("remitEntity : " + remitEntity.toString());
        remitRepository.save(remitEntity);

        PayMentRequest payMentRequest = new PayMentRequest();
        if ("2".equals(remitDto.getRemitCode()) && !remitDto.getOppoUserId().isEmpty()) {
            payMentRequest.setSendId(remitDto.getUserId());
            payMentRequest.setEwalletId(remitDto.getEwalletId());
            payMentRequest.setReceiveId(remitDto.getOppoUserId());
            payMentRequest.setSendAmt(remitDto.getAmt());

            List<PayMentResponse> payMentResponseList = payMentServiceClient.createPayMent(payMentRequest);
        }

        return mapper.map(remitEntity, RemitDto.class);

    }

    @Override
    public RemitDto createRemitFromPayMent(RemitDto remitDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RemitEntity remitEntity = mapper.map(remitDto, RemitEntity.class);
        log.info("remitEntity : " + remitEntity.toString());
        remitRepository.save(remitEntity);

        return mapper.map(remitEntity, RemitDto.class);
    }
}
