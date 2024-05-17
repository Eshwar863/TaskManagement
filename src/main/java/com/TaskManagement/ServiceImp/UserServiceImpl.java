package com.TaskManagement.ServiceImp;

import com.TaskManagement.Dto.UserDto;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    UserRepo userRepo;
@Autowired
    PasswordEncoder passwordEncoder;
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
//            and userdto data to user by using this methods defined below
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users users =  userRepo.save(userDtoToEntity(userDto));
        return EntityToUserDto(users);
    }
    private Users userDtoToEntity(UserDto userDto){

        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        return users;
    }

    private UserDto EntityToUserDto(Users users){
        UserDto userDto=new UserDto();
        userDto.setId(users.getId());
        userDto.setName(users.getName());
        userDto.setEmail(users.getEmail());
        userDto.setPassword(users.getPassword());
        return  userDto;
    }
}
