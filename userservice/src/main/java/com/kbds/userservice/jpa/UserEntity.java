package com.kbds.userservice.jpa;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(length = 20)
    private String userId;
    private String pwd;
    private String encryptedPwd;
    private String username;
    private String phone;
    @CreationTimestamp
    private LocalDateTime lastAccessTime;
}
