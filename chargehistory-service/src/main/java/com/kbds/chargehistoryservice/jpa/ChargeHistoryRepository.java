package com.kbds.chargehistoryservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface ChargeHistoryRepository extends CrudRepository<ChargeHistoryEntity, Long> {

    Iterable<ChargeHistoryEntity> findByUserId(String userId);

    Iterable<ChargeHistoryEntity> findTop5ByUserIdOrderByTransactionTimeDesc(String userId);
}
