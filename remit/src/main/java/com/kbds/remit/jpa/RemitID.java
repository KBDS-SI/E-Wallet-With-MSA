package com.kbds.remit.jpa;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemitID implements Serializable {

    private String userId;
    private String ewalletId;
    private Date transTime;
}
