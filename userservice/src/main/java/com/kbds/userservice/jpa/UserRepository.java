package com.kbds.userservice.jpa;

import com.kbds.userservice.dto.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByUserIdAndPwd(String userId, String pwd);
}
