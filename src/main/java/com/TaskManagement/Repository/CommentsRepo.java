package com.TaskManagement.Repository;

import com.TaskManagement.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments,Long> {
}
