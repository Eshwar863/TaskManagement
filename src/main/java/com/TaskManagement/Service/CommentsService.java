package com.TaskManagement.Service;

import com.TaskManagement.Dto.CommentsDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentsService {
    CommentsDto createComment(String albumid, long userid, CommentsDto commentsDto);
}
