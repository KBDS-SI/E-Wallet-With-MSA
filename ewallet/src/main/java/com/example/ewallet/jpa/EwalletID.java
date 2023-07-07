package com.example.ewallet.jpa;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EwalletID implements Serializable {
//    private String userId;
    private String ewalletId;
//    private Long id;
}
