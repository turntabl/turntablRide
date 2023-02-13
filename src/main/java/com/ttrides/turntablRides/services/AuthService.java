package com.ttrides.turntablRides.services;

import com.ttrides.turntablRides.config.JwtService;
import com.ttrides.turntablRides.models.User;
import com.ttrides.turntablRides.payloads.AuthenticationResponse;
import com.ttrides.turntablRides.payloads.LoginRequest;
import com.ttrides.turntablRides.payloads.RegisterRequest;
import com.ttrides.turntablRides.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User
                .builder()
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken( user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
