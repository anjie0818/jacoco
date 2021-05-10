package com.neo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello1")
    public String index1() {
        return "Hello World1";
    }
    @RequestMapping("/hello2")
    public String index2() {
        return "Hello World2";
    }
}