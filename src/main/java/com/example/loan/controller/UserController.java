package com.example.loan.controller;

import com.example.loan.entities.User;
import com.example.loan.request.UserCreateRequest;
import com.example.loan.response.UserCreateResponse;
import com.example.loan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    public final UserService userService;

    @GetMapping("")
    List<User> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest  ){
        return userService.createUser(userCreateRequest) ;
    }

    @PutMapping("{userId}")
    public User updateUser(@PathVariable("userId") Long userId , @RequestBody UserCreateRequest userCreateRequest){
        return userService.updateUser(userId, userCreateRequest) ;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        userService.deleteById(userId);
    }
}
