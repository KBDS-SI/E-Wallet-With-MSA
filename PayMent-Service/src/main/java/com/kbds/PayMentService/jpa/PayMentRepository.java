package com.kbds.PayMentService.jpa;

import org.springframework.data.repository.CrudRepository;

public interface PayMentRepository extends CrudRepository<PayMentEntity, Long> {

    Iterable<PayMentEntity> findByReceiveId(String receiveId);

    PayMentEntity findByUseId(Long useId);


}
