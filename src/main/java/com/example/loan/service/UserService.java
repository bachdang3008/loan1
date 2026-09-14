package com.example.loan.service;

import com.example.loan.entities.User;
import com.example.loan.reponsitory.UserReponsitory;
import com.example.loan.request.UserCreateRequest;
import com.example.loan.response.UserCreateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserReponsitory userReponsitory;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        if (userReponsitory.existsByEmail(userCreateRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .name(userCreateRequest.getName())
                .address(userCreateRequest.getAddress())
                .phone(userCreateRequest.getPhone())
                .age(userCreateRequest.getAge())
                .roles(Set.of("USER"))
                .build();

        userReponsitory.save(user);

        return UserCreateResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .age(user.getAge())
                .roles(Set.of("USER"))
                .build();
    }


    public User updateUser(Long id, UserCreateRequest userCreateRequest) {
        User user = userReponsitory.findById(id);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setEmail(userCreateRequest.getEmail());
        user.setPassword(userCreateRequest.getPassword());
        user.setName(userCreateRequest.getName());
        user.setAddress(userCreateRequest.getAddress());
        user.setPhone(userCreateRequest.getPhone());
        user.setAge(userCreateRequest.getAge());

        return userReponsitory.save(user);

    }


    public List<User> getAllUsers() {
        return userReponsitory.findAll();
    }

    public void deleteById(int id) {
        if (userReponsitory.findById(id) == null) {
            throw new RuntimeException("User not found");
        }
        userReponsitory.deleteById(id);
    }

}



