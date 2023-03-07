package com.devolpay.secondclient.controller;

import com.devolpay.secondclient.feign.FClient;
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
    public String feignLlamada() {
        return "\n A: " + feignQuestion +  "\n B: " + fClient.getLlamada();
    }




}
