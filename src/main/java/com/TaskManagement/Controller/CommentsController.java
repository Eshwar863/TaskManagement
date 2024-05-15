package com.TaskManagement.Controller;

import com.TaskManagement.Dto.CommentsDto;
import com.TaskManagement.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;
    @PostMapping("{albumid}/comment/{userid}")
    public CommentsDto addComment(@PathVariable(name = "albumid") String albumid,
                                  @PathVariable (name = "userid") long userid,
                                  @RequestBody CommentsDto commentsDto)
    {
        return  commentsService.createComment(albumid,userid,commentsDto);
    }
}
