package com.example.demo.form;

import com.example.demo.entity.Account;
import com.example.demo.entity.Enum.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentCreatingForm {
    private String name;
    private Type type;
    private List<Account> accountList;
}
