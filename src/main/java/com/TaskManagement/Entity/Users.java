package com.TaskManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users  {

    public void setId(Long id) {
        this.id = id;
    }


    @Getter
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    @NotNull
    private String userName;
    @Getter
    @Setter
    @NotNull
    private String email;
    @Setter
    @Getter
    @NotNull
    private String password;

    private UserRole userRole;

}
