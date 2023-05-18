package com.example.demo.entity.dto;

import com.example.demo.entity.Enum.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


import java.util.Date;

@Data
@NoArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private Integer id;
    private String name;
    private Type type;
    private Integer totalMember;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
//    private List<AccountDTO> accountList;
}
