package com.TaskManagement.Controller;

import com.TaskManagement.Dto.TaskDto;
import com.TaskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {
@Autowired
TaskService taskService;
    @PostMapping("{id}/task")
    public TaskDto createTasks(@PathVariable (name = "id") long userid , @RequestBody TaskDto taskDto){
    return  taskService.createTask(userid,taskDto);
    }

    @GetMapping("{userid}/tasks")
    public List<TaskDto> getalltasks(@PathVariable (name = "userid") long userid ){
        return taskService.getAllTasks(userid);
    }
    @GetMapping("{userid}/task/{taskid}")
    public TaskDto getSpecificTask(@PathVariable (name = "userid")long userid ,@PathVariable(name = "taskid") long taskid){
        return taskService.getTask(userid,taskid);
    }

    @DeleteMapping("{userid}/task/{taskid}")
    public void deleteTask(@PathVariable (name = "userid") long userid,@PathVariable (name = "taskid") long taskid){
        taskService.deleteTask(userid,taskid);
    }
}
