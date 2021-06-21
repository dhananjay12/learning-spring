package com.mynotes.spring.demo.restapitesting;

import com.mynotes.spring.demo.restapitesting.exceptions.RestTemplateResponseErrorHandler;
import java.io.IOException;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
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
		restTemplate.setInterceptors(Collections.singletonList(
				new MyClientHttpRequestInterceptor())
		);
		return restTemplate;
	}

}

class MyClientHttpRequestInterceptor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		//Modify the HTTP request header....
		request.getHeaders().add("foo", "fooValue");

		ClientHttpResponse response= execution.execute(request, body);
		return response;
	}
}
