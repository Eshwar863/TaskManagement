package com.TaskManagement.Controller;

import com.TaskManagement.Dto.LoginDto;
import com.TaskManagement.Dto.UserDto;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("register")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userdto){
        return new ResponseEntity<> (userService.createUser(userdto),HttpStatus.CREATED);
    }
    @PostMapping("login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User Login SucessFully",HttpStatus.OK);}
}
