package com.TaskManagement.Dto;

import com.TaskManagement.Entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDto implements UserDetails {
    private Users user;

    public UserDto(String userName, String email, String password) {
        UserName = userName;
        this.email = email;
        this.password = password;
    }

    public UserDto(Users user) {
        this.user = user;
    }

    public String getName() {
        return UserName;
    }

    public void setName(String name) {
        this.UserName = UserName;
    }
    private long id;
    @JsonProperty("username")
    @NotNull
    private String UserName;
    private String email;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
