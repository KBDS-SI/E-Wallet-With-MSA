package com.kbds.PayMentService.service;

import com.kbds.PayMentService.dto.PayMentDto;
import com.kbds.PayMentService.jpa.PayMentEntity;
import com.kbds.PayMentService.jpa.PayMentRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class PayMentServiceImp implements PayMentService{
    PayMentRepository payMentRepository;

    @Autowired
    public PayMentServiceImp(PayMentRepository payMentRepository) {
        this.payMentRepository = payMentRepository;
    }

    @Override
    public PayMentDto createPayMent(PayMentDto paymentDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PayMentEntity payMentEntity = mapper.map(paymentDto, PayMentEntity.class);

        payMentRepository.save(payMentEntity);

        PayMentDto returnPayMentDto = mapper.map(payMentEntity, PayMentDto.class);
        return returnPayMentDto;
    }

    @Override
    public Iterable<PayMentEntity> getReceivePayList(String receiveId) {
        return payMentRepository.findByReceiveId(receiveId);
    }
}
