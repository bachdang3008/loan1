package com.example.loan.controller;

import com.example.loan.request.LoginRequest;
import com.example.loan.response.LoginReponse;
import com.example.loan.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    LoginReponse loginReponse(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
