package com.TaskManagement.ServiceImp;

import com.TaskManagement.Dto.UserDto;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.UserService;
import com.TaskManagement.ServiceImp.Jwt.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
@Autowired
private ModelMapper modelMapper;
//    @Override public UserDto createUser(UserDto userDto) {
//        Users users = new ModelMapper().map(userDto, Users.class);
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userRepo.save(users);
//        UserDto userDto1 = new ModelMapper().map(users,UserDto.class);
//        return userDto1;
//    }

//   here we converted the users date to userdto
//            and userdto data to user by using these methods defined below
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
       // userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users users =  userRepo.save(userDtoToEntity(userDto));
        return EntityToUserDto(users);
    }
    private Users userDtoToEntity(UserDto userDto){

        Users users = new Users();
        users.setUserName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        return users;
    }

    private UserDto EntityToUserDto(Users users){
        UserDto userDto = new UserDto(users);
        userDto.setId(users.getId());
        userDto.setName(users.getUserName());
        userDto.setEmail(users.getEmail());
        userDto.setPassword(users.getPassword());
        return  userDto;
    }

    public String verify(UserDto userDto){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userDto.getName(),userDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDto.getName());
        }
        else
            return "Session Expired";
    }
    }

