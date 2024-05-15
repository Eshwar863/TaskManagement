package com.TaskManagement.ServiceImp;

import com.TaskManagement.Dto.CommentsDto;
import com.TaskManagement.Entity.Comments;
import com.TaskManagement.Entity.Users;
import com.TaskManagement.Exceptions.UserNotFound;
import com.TaskManagement.Repository.CommentsRepo;
import com.TaskManagement.Repository.UserRepo;
import com.TaskManagement.Service.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImp implements CommentsService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CommentsRepo commentsRepo;
    @Override
   public CommentsDto createComment(String albumid, long userid, CommentsDto commentsDto){
        Users users = userRepo.findById(userid).orElseThrow(
                () -> new UserNotFound(String.format("UserId %d Doesn't Exist",userid))
        );
        Comments comments  = new ModelMapper().map(commentsDto, Comments.class);
        comments.setUsers(users);
        Comments comments1 = commentsRepo.save(comments);
        return new ModelMapper().map(comments1 ,CommentsDto.class);
    }
}
