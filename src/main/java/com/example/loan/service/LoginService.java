package com.example.loan.service;

import com.example.loan.request.LoginRequest;
import com.example.loan.response.LoginReponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public LoginReponse login (LoginRequest loginRequest) {

        return LoginReponse.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }
}
