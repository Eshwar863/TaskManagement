package com.TaskManagement.ServiceImp.Jwt;

import com.TaskManagement.Dto.UserDto;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Exceptions.UserNotFound;
import com.TaskManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDtoService implements org.springframework.security.core.userdetails.UserDetailsService
{
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Users user = userRepo.findByUserName(username);
        if(user == null ){
            throw new UserNotFound("User not found");
        }
        else{
        return new UserDto(user);
    }}
}
