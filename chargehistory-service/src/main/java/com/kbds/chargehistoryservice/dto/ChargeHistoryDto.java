package com.kbds.chargehistoryservice.dto;

import com.kbds.chargehistoryservice.vo.ChargeHistoryResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ChargeHistoryDto {
    private String userId;
    private String ewalletId;
    private Date transactionTime;
    private BigDecimal amt;
    private String memo;
    private BigDecimal finalAmt;

    private List<ChargeHistoryResponse> listChargeHistoryResponses;
}
