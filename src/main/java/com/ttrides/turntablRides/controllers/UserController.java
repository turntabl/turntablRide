package com.ttrides.turntablRides.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/login")
    public String loginUser() {
        return "Secured endpoint";
    }

}
