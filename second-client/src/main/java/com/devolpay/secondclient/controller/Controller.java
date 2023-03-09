package com.devolpay.secondclient.controller;

import com.devolpay.secondclient.feign.FClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Value("${answer.rtemplate}")
    private String rtAnswer;
    @Value("${question.feign}")
    private String feignQuestion;

    private final FClient fClient;

    public Controller(FClient fClient) {
        this.fClient = fClient;
    }

    @GetMapping("/rt-llamada")
    public String rtLlamada() {
        return rtAnswer;
    }

    //curl http://localhost:8081/feign
    @GetMapping("/feign")
    @CircuitBreaker(name = "first-client", fallbackMethod = "fallbackFeign")
    public String feignLlamada() {
            return "\n A: " + feignQuestion +  "\n B: " + fClient.getLlamada();
    }

    public String fallbackFeign(Throwable throwable) {
        return "Ha ocurrido un problema al intentar realizar la llamada.";
    }


}
