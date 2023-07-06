package com.example.ewallet.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEwallet {
    private String userId;
    private String ewalletId;
    private BigDecimal amt;
}
