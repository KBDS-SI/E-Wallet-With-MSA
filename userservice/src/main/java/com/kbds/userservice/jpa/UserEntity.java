package com.kbds.userservice.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    private String userId;

    @Column
    private String pwd;

    @Column
    private String username;

    @Column
    private String phone;

    @Column
    private LocalDateTime lastAccessTime;
}
