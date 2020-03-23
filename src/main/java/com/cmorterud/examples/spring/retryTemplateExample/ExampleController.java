package com.cmorterud.examples.spring.retryTemplateExample;

import com.cmorterud.examples.spring.retryTemplateExample.exception.RetryableException;
import com.cmorterud.examples.spring.retryTemplateExample.resource.BrittleResource;
import com.cmorterud.examples.spring.retryTemplateExample.resource.ReliableResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@EnableAutoConfiguration
@RequestMapping("/resource")
public class ExampleController {
    @Autowired
    private ReliableResource reliableResource;

    @Autowired
    private BrittleResource brittleResource;

    @RequestMapping("/getResource")
    ResponseEntity<String> getResource(){
        return new ResponseEntity<String>(reliableResource.getResource(), HttpStatus.OK);
    }

    @RequestMapping("/getBrittleResource")
    ResponseEntity<String> getBrittle() {
        String brittleResourceData;

        try {
            brittleResourceData = brittleResource.getBrittleResource();
        } catch (RetryableException e) {
            brittleResourceData = null;
        }

        if(brittleResourceData == null) {
            return new ResponseEntity<>("failed to retrieve data!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(brittleResourceData, HttpStatus.OK);
    }
}
