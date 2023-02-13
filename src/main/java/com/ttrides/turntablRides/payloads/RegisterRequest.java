package com.ttrides.turntablRides.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterRequest {
    private final String email;
    private final String username;
    private final String password;
}
