package com.example.remit.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RequestRemit {
    private String userId;
    private String ewalletId;
    private Date transTime;
    private String remitCode;
    private BigDecimal amt;
    private String memo;
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;
}
