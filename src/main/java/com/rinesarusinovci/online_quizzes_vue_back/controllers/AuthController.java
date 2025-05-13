package com.rinesarusinovci.online_quizzes_vue_back.controllers;


import com.rinesarusinovci.online_quizzes_vue_back.dto.LoginDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.RegisterUserDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto request) {
        var user = authenticationService.authenticate(request.getEmail(), request.getPassword());
        var token = authenticationService.generateToken(user);

        var authResponse = new AuthResponse(token, 86400L);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterUserDto request) {
        authenticationService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

}


