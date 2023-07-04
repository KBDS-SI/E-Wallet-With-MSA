package com.kbds.PayMentService.vo;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class PayMentRequest {
    @NotNull(message = "SendId cannot be null")
    @Size(min = 2, message = "SendId not be less than to characters")
    private String sendId;

    @NotNull(message = "ReceiveId cannot be null")
    @Size(min = 2, message = "ReceiveId not be less than to characters")
    private String receiveId;

    @NotNull(message = "SendAmt cannot be null")
    @Size(min = 2, message = "SendAmt not be less than to numbers")
    private Integer sendAmt;
}
