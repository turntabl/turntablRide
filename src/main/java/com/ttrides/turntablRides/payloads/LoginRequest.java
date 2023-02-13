package com.ttrides.turntablRides.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {
    private final String email;
    private final String password;
}
