package com.kbds.PayMentService.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="payment")
public class PayMentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long useId;

    @Column(nullable = false, unique = true)
    private String sendId;
    @Column(nullable = false, unique = true)
    private String receiveId;
    @Column(nullable = false)
    private Integer sendAmt;
    @Column(nullable = false)
    private String ewalletId;

    @CreationTimestamp
    @Column(name = "send_at", unique = true)
//    @Column(nullable = true, updatable = false, insertable = false)
//    @ColumnDefault(value = "CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date sendAt;
    private LocalDateTime sendAt;
}