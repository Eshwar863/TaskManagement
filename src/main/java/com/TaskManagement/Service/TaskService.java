package com.TaskManagement.Service;

import com.TaskManagement.Dto.TaskDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    TaskDto createTask(long userid, TaskDto taskDto);

    List<TaskDto> getAllTasks(long userid);
    TaskDto getTask(long userid ,long taskid);

    void deleteTask(long userid, long taskid);
}
