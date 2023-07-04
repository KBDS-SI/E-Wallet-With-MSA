package com.kbds.chargehistoryservice.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ChargeHistoryRequest {
    @NotNull(message = "UserId connot be null")
    @Size(min = 2, message = "UserId not be less than to characters")
    private String userId;
    @NotNull(message = "EWalletId connot be null")
    @Size(min = 2, message = "EWalletId not be less than to characters")
    private String ewalletId;
    @NotNull(message = "AMT cannot be null")
    @Size(min = 2, message = "AMT not be less than to numbers")
    private BigDecimal amt;
    @NotNull(message = "MEMO connot be null")
    @Size(min = 1, message = "EWalletId not be less than to characters")
    private String memo;
}
