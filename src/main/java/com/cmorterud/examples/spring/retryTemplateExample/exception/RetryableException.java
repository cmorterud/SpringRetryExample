package com.cmorterud.examples.spring.retryTemplateExample.exception;

public class RetryableException extends Exception {
    public RetryableException(String message){
        super(message);
    }
}
