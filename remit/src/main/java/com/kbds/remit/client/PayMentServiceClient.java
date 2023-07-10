package com.kbds.remit.client;

import com.kbds.remit.vo.PayMentRequest;
import com.kbds.remit.vo.PayMentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "payment", url = "http://192.168.61.190:8000")
public interface PayMentServiceClient {

    @PostMapping("/payment-service/createPayMent")
    List<PayMentResponse> createPayMent(@RequestBody PayMentRequest payMentRequest);
}
