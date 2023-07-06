package com.kbds.remit.vo;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RequestRemit {
    @NotNull(message = "SendId cannot be null")
    @Size(min = 5, message = "SendId not be less than to characters")
    private String userId;
    private String ewalletId;
    private String remitCode;
    private BigDecimal amt;
    private String memo;
    @NotNull(message = "oppoUserId cannot be null")
    @Size(min = 5, message = "oppoUserId not be less than to characters")
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;
}
