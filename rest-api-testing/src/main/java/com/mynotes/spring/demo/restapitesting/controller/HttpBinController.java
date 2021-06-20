package com.mynotes.spring.demo.restapitesting.controller;

import com.mynotes.spring.demo.restapitesting.exceptions.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bin")
public class HttpBinController {

    private final RestTemplate restTemplate;


    private final String baseURL;

    public HttpBinController(RestTemplate restTemplate,
                             @Value("${my.service.url:https://httpbin.org}") String baseURL){
        this.restTemplate = restTemplate;
        this.baseURL = baseURL;
    }

    @GetMapping("/rest/get")
    public ResponseEntity<?> restTemplateGet(){
       return  this.restTemplate.getForEntity(baseURL+"/get",String.class);
    }


}
