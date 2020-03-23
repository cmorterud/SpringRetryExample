package com.cmorterud.examples.spring.retryTemplateExample.resource;

import org.springframework.stereotype.Service;


@Service
public class ReliableResource {

    public String getResource(){
        return "Resource";
    }
}
