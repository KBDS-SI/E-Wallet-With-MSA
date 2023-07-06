package com.kbds.remit.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemitRepository extends CrudRepository<RemitEntity, Long> {

    List<RemitEntity> findByUserIdOrderByTransTimeDesc(String userId);

}
