package com.TaskManagement.Controller;

import com.TaskManagement.Dto.UserDto;
import com.TaskManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping("register")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userdto){
        return new ResponseEntity<> (userService.createUser(userdto),HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public String createuser(){
        return "You are in Login Page";
    }

    @GetMapping("/user")
    public String user(){
        return "You are User page";
    }}