package com.devolpay.secondclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "first-client")
public interface FClient {

    @GetMapping("/feign-llamada")
    String getLlamada();

}
