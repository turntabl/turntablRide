package com.ttrides.turntablRides.controllers;

import com.ttrides.turntablRides.models.response.TTResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TTResponse> loginUser() {
        return ResponseEntity.ok().body(TTResponse.builder().statusCode(200).statusText("Authorised").message("Secured endpoint").build());
    }
}
