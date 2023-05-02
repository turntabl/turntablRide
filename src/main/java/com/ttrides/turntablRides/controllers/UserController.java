package com.ttrides.turntablRides.controllers;

import com.ttrides.turntablRides.models.response.TTResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ttrides.turntablRides.models.UserModel;
import com.ttrides.turntablRides.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TTResponse> loginUser() {
        return ResponseEntity.ok().body(TTResponse.builder().statusCode(200).statusText("Authorised").message("Secured endpoint").build());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> listAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUserProfile(@RequestBody UserModel user) {
        return ResponseEntity.ok().body(userService.insertUserDetails(user));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<UserModel>> getUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserModel> updateUserProfile(@PathVariable int userId, @RequestBody UserModel user) {
        return ResponseEntity.accepted().body(userService.updateUserDetails(userId, user));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable int userId) {
        userService.deleteUserDetails(userId);
        return ResponseEntity.ok().body("User with Profile ID: " + userId + " is deleted");
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<Optional<UserModel>> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.fetchUserByEmail(email));
    }

}
