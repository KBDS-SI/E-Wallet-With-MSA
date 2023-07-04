package com.kbds.chargehistoryservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ChargeHistoryDto {
    private String userId;
    private String ewalletId;
    private Date transactionTime;
    private BigDecimal amt;
    private String memo;
    private BigDecimal finalAmt;
}
