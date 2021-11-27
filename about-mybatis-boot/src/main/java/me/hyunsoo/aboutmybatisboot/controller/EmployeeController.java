package me.hyunsoo.aboutmybatisboot.controller;

import lombok.RequiredArgsConstructor;
import me.hyunsoo.aboutmybatisboot.beans.Employee;
import me.hyunsoo.aboutmybatisboot.mapper.EmployeeMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;


    @PostMapping("")
    public int post(@RequestBody Employee employee){
        return employeeMapper.insert(employee);
    }

    @GetMapping("")
    public List<Employee> getAll(){
        return employeeMapper.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id){
        return employeeMapper.getById(id);
    }
}
