package com.ttrides.turntablRides.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/login")
public class UserController {

    @GetMapping
    public String currentUser() {
        return "Secured endpoint";
    }
}
