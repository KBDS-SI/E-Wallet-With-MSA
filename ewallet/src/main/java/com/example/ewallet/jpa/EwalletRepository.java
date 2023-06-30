package com.example.ewallet.jpa;

import org.springframework.data.repository.CrudRepository;

public interface EwalletRepository extends CrudRepository<EwalletEntity, EwalletID> {
    EwalletEntity findByEwalletId(EwalletID ewalletID);
}
