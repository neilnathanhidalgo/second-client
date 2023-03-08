package com.devolpay.secondclient.controller;

import com.devolpay.secondclient.feign.FClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
        if (fClient.getLlamada().equals("Ha ocurrido un problema al intentar realizar la llamada.")){
            return fClient.getLlamada();
        }else {
            return "\n A: " + feignQuestion +  "\n B: " + fClient.getLlamada();
        }
    }

}
