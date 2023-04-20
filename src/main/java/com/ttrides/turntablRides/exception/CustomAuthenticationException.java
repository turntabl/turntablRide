package com.ttrides.turntablRides.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}