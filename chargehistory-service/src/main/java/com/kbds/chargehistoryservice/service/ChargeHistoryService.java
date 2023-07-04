package com.kbds.chargehistoryservice.service;

import com.kbds.chargehistoryservice.dto.ChargeHistoryDto;
import com.kbds.chargehistoryservice.jpa.ChargeHistoryEntity;

public interface ChargeHistoryService {
    ChargeHistoryDto createChargeHistory(ChargeHistoryDto chargeHistoryDto);

    Iterable<ChargeHistoryEntity> getChargeHistory(String userId);
}
