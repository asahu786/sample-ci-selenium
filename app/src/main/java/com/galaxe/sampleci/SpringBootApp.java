package com.galaxe.sampleci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}

@RestController
class HelloController {
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello from Spring Boot App!";
    }
}
