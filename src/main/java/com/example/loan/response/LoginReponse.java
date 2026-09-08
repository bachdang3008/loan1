package com.example.loan.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginReponse {
    private String email;
    private String password;
}
