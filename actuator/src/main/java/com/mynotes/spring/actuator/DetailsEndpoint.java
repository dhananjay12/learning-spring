package com.mynotes.spring.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "details")
public class DetailsEndpoint {

    @ReadOperation
    public String details(){
        return "My App Details";
    }
}
