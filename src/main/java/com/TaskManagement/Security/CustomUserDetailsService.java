package com.TaskManagement.Security;

import com.TaskManagement.Entity.Users;
import com.TaskManagement.Exceptions.UserNotFound;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepo.findByEmail(email).orElseThrow(
                ()-> new UserNotFound(String.format("User with email %s not Found ",email))
        );
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        return new User(users.getEmail(),users.getPassword(),userAuthorities(roles));
    }
    private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
        return  roles.stream().map(
            role -> new SimpleGrantedAuthority(role)

        ).collect(Collectors.toList());
    }
}
