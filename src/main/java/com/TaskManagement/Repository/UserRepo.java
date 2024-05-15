package com.TaskManagement.Repository;

import com.TaskManagement.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users,Long> {

}