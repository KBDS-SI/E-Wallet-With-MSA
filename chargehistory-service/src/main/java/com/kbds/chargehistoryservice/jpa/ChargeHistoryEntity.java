package com.kbds.chargehistoryservice.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="ChargeHistory")
public class ChargeHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String ewalletId;

    @CreationTimestamp
    @Column(nullable = false, unique = true)
    private LocalDateTime transactionTime;

    @Column(nullable = false)
    private BigDecimal amt;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private BigDecimal finalAmt;
}
