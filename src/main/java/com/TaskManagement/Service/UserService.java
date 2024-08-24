package com.TaskManagement.Service;

import com.TaskManagement.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDto createUser(UserDto userDto);

    String verify(UserDto user);
}
