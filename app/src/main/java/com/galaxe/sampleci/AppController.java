package com.galaxe.sampleci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<html><head><title>My Sample App</title></head><body><h1>Hello World</h1></body></html>";
    }
}
