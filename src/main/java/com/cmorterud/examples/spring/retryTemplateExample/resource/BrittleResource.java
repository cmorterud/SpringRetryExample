package com.cmorterud.examples.spring.retryTemplateExample.resource;

import com.cmorterud.examples.spring.retryTemplateExample.exception.RetryableException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BrittleResource {

    @Retryable(include = RetryableException.class)
    public String getBrittleResource() throws RetryableException{
        int result = new Random().nextInt() % 2;
        if(result == 0){
            System.out.println("Failed to retrieve data!");
            throw new RetryableException("Brittle service failure");
        }
        return "Brittle resource";
    }
}
