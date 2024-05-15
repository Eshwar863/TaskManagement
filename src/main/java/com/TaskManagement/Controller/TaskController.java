package com.TaskManagement.Controller;

import com.TaskManagement.Dto.TaskDto;
import com.TaskManagement.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {
@Autowired
TaskService taskService;
    @PostMapping("{id}/task")
    public ResponseEntity<TaskDto> createTasks(@PathVariable (name = "id") long userid , @RequestBody TaskDto taskDto){
    return new ResponseEntity<>( taskService.createTask(userid,taskDto), HttpStatus.CREATED);
    }

    @GetMapping("{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getalltasks(@PathVariable (name = "userid") long userid ){
        return new ResponseEntity<>( taskService.getAllTasks(userid),HttpStatus.OK);
    }
    @GetMapping("{userid}/task/{taskid}")
    public ResponseEntity<TaskDto> getSpecificTask(@PathVariable (name = "userid")long userid ,@PathVariable(name = "taskid") long taskid){
        return new ResponseEntity<>(taskService.getTask(userid,taskid),HttpStatus.OK);
    }

    @DeleteMapping("{userid}/task/{taskid}")
    public ResponseEntity<String> deleteTask(@PathVariable (name = "userid") long userid,@PathVariable (name = "taskid") long taskid){
        taskService.deleteTask(userid,taskid);
        ResponseEntity.ok("Removed from the List successfully.");
    return new ResponseEntity<>(HttpStatus.OK);
    }
}
