package com.example.demo.form;

import com.example.demo.entity.Enum.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountUpdatingForm {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Integer departmentId;
}
