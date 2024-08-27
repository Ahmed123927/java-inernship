package com.example.springdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/test-header")
    public String getHeaderValue(@Reqe(value = "X-Test-Header", defaultValue = "default") String headerValue) {
        return "Header Value: " + headerValue;
    }
}
