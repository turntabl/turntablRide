package com.ttrides.turntablRides.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vi/demo")
public class SampleController {

    @GetMapping
    public String currentUser() {
        return "Secured endpoint";
    }
}
