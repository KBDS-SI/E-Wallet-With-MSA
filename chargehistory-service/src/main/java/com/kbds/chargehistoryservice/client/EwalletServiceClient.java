package com.kbds.chargehistoryservice.client;

import com.example.ewallet.vo.ResponseEwallet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ewallet", url = "http://192.168.61.190:8000")
public interface EwalletServiceClient {
    @GetMapping("/ewallet-service/searchWallet/{userId}")
    ResponseEntity<ResponseEwallet> getSearchEwallet(@PathVariable String userId);
}
