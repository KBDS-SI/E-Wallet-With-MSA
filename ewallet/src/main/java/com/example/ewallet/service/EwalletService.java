package com.example.ewallet.service;

import com.example.ewallet.dto.EwalletDto;

public interface EwalletService {
    EwalletDto createEwallet(EwalletDto ewalletDto);
    EwalletDto searchEwallet(String userId);

    EwalletDto updateBalance(EwalletDto ewalletDto);
}
