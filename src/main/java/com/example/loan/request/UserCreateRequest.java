package com.example.loan.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserCreateRequest {
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
    private int age;
}
