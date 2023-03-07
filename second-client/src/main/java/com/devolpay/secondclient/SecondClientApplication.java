package com.devolpay.secondclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class SecondClientApplication {

	@Value("${client.status}")
	private String status;

	public static void main(String[] args) {
		SpringApplication.run(SecondClientApplication.class, args);
	}

	@GetMapping("/status")
	public String status() {
		return status;
	}
}
