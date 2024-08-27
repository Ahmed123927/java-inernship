package com.example.elearningplatform.controller;

import com.example.elearningplatform.dto.AuthenticationRequest;
import com.example.elearningplatform.dto.AuthenticationResponse;
import com.example.elearningplatform.dto.RegisterRequest;
import com.example.elearningplatform.service.AuthenticationService;
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
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));

    }
}
