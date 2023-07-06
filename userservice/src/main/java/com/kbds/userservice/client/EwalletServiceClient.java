package com.kbds.userservice.client;

import com.kbds.userservice.vo.RequestEwallet;
import com.kbds.userservice.vo.ResponseEwallet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ewallet-service", url = "http://192.168.61.252:8000")
public interface EwalletServiceClient {
    @PostMapping("/ewallet-service/createEwallet")
    ResponseEwallet createEwallet(@RequestBody RequestEwallet ewallet);
}
