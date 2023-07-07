package com.example.ewallet.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface EwalletRepository extends CrudRepository<EwalletEntity, EwalletID> {
    EwalletEntity findByEwalletId(EwalletID ewalletID);

    EwalletEntity findByUserId(String userId);

}
