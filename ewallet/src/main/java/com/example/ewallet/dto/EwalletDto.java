package com.example.ewallet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EwalletDto {
    private String userId;
    private String ewalletId;
    private BigDecimal amt;
    private String addYn;

    private Date createdAt;
}
