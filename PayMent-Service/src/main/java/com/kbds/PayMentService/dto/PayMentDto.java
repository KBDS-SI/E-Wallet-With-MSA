package com.kbds.PayMentService.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayMentDto implements Serializable {
    private String sendId;
    private String receiveId;
    private BigDecimal sendAmt;
    private Date sendAt;

    private Long useId;
}
