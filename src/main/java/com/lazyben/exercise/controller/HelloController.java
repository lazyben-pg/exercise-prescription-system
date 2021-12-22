package com.lazyben.exercise.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String sayHello(@RequestBody Map<String, String> usernameAndPassword) {
        System.out.println(usernameAndPassword.get("username"));
        return "hello";
    }
}
