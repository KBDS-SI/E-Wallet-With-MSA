package com.example.ewallet.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestEwallet {
    private String userId;
    private String ewalletId;
    private BigDecimal amt;
}
