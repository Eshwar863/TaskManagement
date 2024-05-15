package com.TaskManagement.ServiceImp;


import com.TaskManagement.Dto.TaskDto;
import com.TaskManagement.Entity.Task;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Exceptions.APIException;
import com.TaskManagement.Exceptions.TaskNotFound;
import com.TaskManagement.Exceptions.UserNotFound;
import com.TaskManagement.Repository.TaskRepo;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.TaskService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TaskRepo taskRepo;
@Autowired
ModelMapper modelMapper;
    @Override
    public TaskDto createTask(long userid, TaskDto taskDto) {
        Users users = userRepo.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("UserId %d Doesn't Exist",userid))
        );
        Task task = new ModelMapper().map(taskDto,Task.class);
        task.setUsers(users);
        Task savedtask = taskRepo.save(task);
        TaskDto taskDto1= new ModelMapper().map(savedtask,TaskDto.class);
       return taskDto1;
    }

     @Override
     public List<TaskDto> getAllTasks(long userid){
        Users users = userRepo.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("UserId %d Doesn't Exist",userid))
        );
         List<Task> tasks = taskRepo.findAllByUsersId(userid);
         return tasks.stream().map(
                 task1 ->modelMapper.map(task1,TaskDto.class)
         ).collect(Collectors.toList());
     }

     public TaskDto getTask(long userid ,long taskid){
        Users users =userRepo.findById(userid).orElseThrow(
                ()-> new UserNotFound(String.format("UserId %d Doesn't Exist",userid))
        );

        Task task = taskRepo.findById(taskid).orElseThrow(
                ()-> new TaskNotFound(String.format("TaskId %d Doesn't Exist",taskid))
        );
        if(users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Task %d is not belong to %d userid",taskid,userid));
        }
         return new ModelMapper().map(task,TaskDto.class);
    }

    public void deleteTask(long userid, long taskid){
        Users users = userRepo.findById(userid).orElseThrow(
                ()-> new UserNotFound(String.format("UserId %d Doesn't Exist",userid))
        );
        Task task = taskRepo.findById(taskid).orElseThrow(
                ()-> new TaskNotFound(String.format("TaskId %d Doesn't Exist",taskid))
        );
        if(users.getId() != task.getUsers().getId()){
            throw new APIException(String.format("Taskid %d not belongs to % userid",userid,taskid));
        }
        else{ taskRepo.deleteById(taskid);}
    }
}
