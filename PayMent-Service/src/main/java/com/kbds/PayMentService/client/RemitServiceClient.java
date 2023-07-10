package com.kbds.PayMentService.client;

import com.kbds.remit.vo.RequestRemit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "remit", url = "http://192.168.61.190:8000")
public interface RemitServiceClient {

    @PostMapping("/remit/remit")
    ResponseEntity createRemit(@RequestBody RequestRemit remit);
}
