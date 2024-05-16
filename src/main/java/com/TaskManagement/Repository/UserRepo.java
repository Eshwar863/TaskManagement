package com.TaskManagement.Repository;

import com.TaskManagement.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);
}