package com.TaskManagement.Dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private long id;
    private String taskname;
}
