package com.example.loan.controller;

import com.example.loan.request.LoginRequest;
import com.example.loan.response.LoginResponse;
import com.example.loan.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationLogin {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    LoginResponse loginResponse(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
