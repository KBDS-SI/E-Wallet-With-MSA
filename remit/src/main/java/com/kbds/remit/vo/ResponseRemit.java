package com.kbds.remit.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ResponseRemit {
    private Long num;
    private String userId;
    private String ewalletId;
    private String remitCode;
    private BigDecimal amt;
    private String memo;
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;
}
