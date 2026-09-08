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

    @GetMapping("/get")
    List<User> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/post")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest  ){
        return userService.createUser(userCreateRequest) ;
    }

    @PutMapping("/put/{iduser}")
    public User updateUser(@PathVariable("iduser") Long iduser , @RequestBody UserCreateRequest userCreateRequest){
        return userService.updateUser(iduser, userCreateRequest) ;
    }

    @DeleteMapping("/delete/{userid}")
    public void deleteUser(@PathVariable("userid") int userid){
        userService.DeleteById(userid);
    }
}
