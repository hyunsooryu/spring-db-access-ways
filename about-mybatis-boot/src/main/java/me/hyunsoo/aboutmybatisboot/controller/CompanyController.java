package me.hyunsoo.aboutmybatisboot.controller;

import lombok.RequiredArgsConstructor;
import me.hyunsoo.aboutmybatisboot.CompanyService;
import me.hyunsoo.aboutmybatisboot.beans.Company;
import me.hyunsoo.aboutmybatisboot.mapper.CompanyMapper;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    private  final CompanyService companyService;

    private final CompanyMapper companyMapper;

    @Transactional(readOnly = true)
    @PostMapping("")
    public int post(@RequestBody Company company){
        System.out.println(2 / 0);
        int result = companyMapper.insertByXml(company);
        System.out.println("posted company : " + company);
        return result;
    }

    @GetMapping("")
    public List<Company> getAll(){
        return companyMapper.getAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable int id){
        return companyMapper.getById(id);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public String sql_exception(Exception e){
        return "SQL ERROR";
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public String arithmetic_exception(Exception e){
        return "ARITHMETIC ERROR";
    }

}
