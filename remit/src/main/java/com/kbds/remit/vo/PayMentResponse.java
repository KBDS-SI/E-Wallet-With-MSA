package com.kbds.remit.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayMentResponse {
    private String useId;
    private String sendId;
    private String receiveId;
    private BigDecimal sendAmt;
    private String ewalletId;
    private LocalDateTime sendAt;
}
