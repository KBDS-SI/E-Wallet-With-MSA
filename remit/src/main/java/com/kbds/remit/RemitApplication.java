package com.kbds.remit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@EnableFeignClients
public class RemitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemitApplication.class, args);
	}


}
