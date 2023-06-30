package com.example.remit.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "remit")
@IdClass(RemitID.class)
public class RemitEntity {

    @Id
    private String userId;
    @Id
    private String ewalletId;
    @Id
    private Date transTime;
    private String remitCode;
    private BigDecimal amt;
    private String memo;
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;
}
