package com.example.demo.form;

import com.example.demo.specification.filter.DateFilter;
import com.example.demo.specification.filter.StringFilter;

import lombok.Data;

@Data
public class DepartmentFilterForm {
    private StringFilter search;
    private DateFilter createdDate;
    private StringFilter type;
}
