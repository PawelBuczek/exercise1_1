package com.kodilla.exercise1_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MController {

    @RequestMapping("/m1")
    @GetMapping
    public String m1() {
        return "m1 success";
    }

    @RequestMapping("/m2")
    @GetMapping
    public String m2() {
        return "m2 success";
    }

    @RequestMapping("/m3")
    @GetMapping
    public String m3() {
        return "m3 success";
    }
}
