package com.devolpay.secondclient.feign;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "first-client")
public interface FClient {

    @Retryable(value = {FeignException.class},
                    maxAttempts = 3,
                    backoff = @Backoff(delay = 3000, maxDelay = 10000, multiplier = 2))
    @GetMapping("/feign-llamada")
    String getLlamada();

}
