package me.hyunsoo.aboutmybatisboot.beans;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Company {
    private int id;
    private String name;
    private String address;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<Employee> employeeList;
}
