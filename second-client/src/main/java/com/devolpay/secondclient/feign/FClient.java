package com.devolpay.secondclient.feign;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "first-client")
@CircuitBreaker(name = "first-client", fallbackMethod = "fallbackFeign")
public interface FClient {

    @Retryable(value = {FeignException.class},
                    maxAttempts = 3,
                    backoff = @Backoff(delay = 3000, maxDelay = 30000, multiplier = 2))
    @GetMapping("/feign-llamada")
    String getLlamada();
    default String fallbackFeign(Throwable throwable) {
        return "Ha ocurrido un problema al intentar realizar la llamada.";
    }

}
