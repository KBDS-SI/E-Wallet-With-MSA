package com.example.ewallet.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="ewallet")
public class EwalletEntity {

//    @Id
    private String userId;
    @Id
    private String ewalletId;

    private BigDecimal amt;
}
