package com.TaskManagement.Repository;

import com.TaskManagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByUsersId(long userid);
}
