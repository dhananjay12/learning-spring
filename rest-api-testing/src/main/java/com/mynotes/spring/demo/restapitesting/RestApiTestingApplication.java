package com.mynotes.spring.demo.restapitesting;

import com.mynotes.spring.demo.restapitesting.exceptions.RestTemplateResponseErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTestingApplication.class, args);
	}

	@Bean
	//@LoadBalanced //Not working with tests
	public RestTemplate restTemplate(RestTemplateResponseErrorHandler handler){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(handler);
		return restTemplate;
	}

}
