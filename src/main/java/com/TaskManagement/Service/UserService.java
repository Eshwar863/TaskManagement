package com.TaskManagement.Service;

import com.TaskManagement.Dto.UserDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDto createUser(UserDto userDto);
}
