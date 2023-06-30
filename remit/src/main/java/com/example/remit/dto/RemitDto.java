package com.example.remit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RemitDto {
    private String userId;
    private String ewalletId;
    private Date transTime;
    private String remitCode;
    //입금출금구분
    private BigDecimal amt;
    private String memo;
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;

}