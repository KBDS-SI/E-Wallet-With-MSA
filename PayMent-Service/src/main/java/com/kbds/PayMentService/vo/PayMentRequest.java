package com.kbds.PayMentService.vo;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
public class PayMentRequest {
    @NotNull(message = "SendId cannot be null")
    @Size(min = 5, message = "SendId not be less than to characters")
    private String sendId;

    @NotNull(message = "ReceiveId cannot be null")
    @Size(min = 5, message = "ReceiveId not be less than to characters")
    private String receiveId;

    @NotNull(message = "SendAmt cannot be null")
    @Size(min = 1, message = "SendAmt not be less than to numbers")
    private BigDecimal sendAmt;

    @NotNull(message = "ewalletId cannot be null")
    @Size(min = 2, message = "ewalletId not be less than to numbers")
    private String ewalletId;

    private Long useId;
}
