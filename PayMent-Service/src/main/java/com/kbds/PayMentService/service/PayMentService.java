package com.kbds.PayMentService.service;


import com.kbds.PayMentService.dto.PayMentDto;
import com.kbds.PayMentService.jpa.PayMentEntity;

public interface PayMentService {

    PayMentDto createPayMent(PayMentDto paymentDto);

    Iterable<PayMentEntity> getReceivePayList(String receiveId);
}