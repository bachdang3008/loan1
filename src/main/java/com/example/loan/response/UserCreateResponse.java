package com.example.loan.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
    private int age;
    private Set<String> roles;
}
