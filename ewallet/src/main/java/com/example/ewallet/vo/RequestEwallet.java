package com.example.ewallet.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RequestEwallet {

    @NotNull
    private String userId;

//    @NotNull
    private String ewalletId;

    @NotNull
    private BigDecimal amt;
}
