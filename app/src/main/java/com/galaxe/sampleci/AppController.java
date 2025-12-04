// app/src/main/java/com/galaxe/sampleci/controller/AppController.java
package com.galaxe.sampleci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot App!";
    }
}