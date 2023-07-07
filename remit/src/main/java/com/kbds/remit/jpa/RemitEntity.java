package com.kbds.remit.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "remit")
//@IdClass(RemitID.class)
public class RemitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String ewalletId;

    @CreationTimestamp
    @Column(name = "trans_time")
    private LocalDateTime transTime;
    private String remitCode;
    private BigDecimal amt;
    private String memo;
    private String oppoUserId;
    private String cancelYn;
    private BigDecimal finBal;
}
