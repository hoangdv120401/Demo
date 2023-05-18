package com.example.demo.form;

import com.example.demo.specification.filter.IntegerFilter;
import com.example.demo.specification.filter.StringFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {
    private IntegerFilter id;
    private StringFilter username;
    private StringFilter search;

}
