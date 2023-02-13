package com.ttrides.turntablRides.payloads;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public record AuthenticationResponse(String token) {

}
