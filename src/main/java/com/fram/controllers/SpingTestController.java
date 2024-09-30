package com.fram.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpingTestController {
    @GetMapping("/")
    public String sayHello(HttpServletRequest request){
        return "Hello, welcome to FellowDev" + request.getSession().getId();
    }
}
