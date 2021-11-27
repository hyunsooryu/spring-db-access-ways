package me.hyunsoo.aboutmybatisboot;


import lombok.RequiredArgsConstructor;
import me.hyunsoo.aboutmybatisboot.beans.Company;
import me.hyunsoo.aboutmybatisboot.mapper.CompanyMapper;
import me.hyunsoo.aboutmybatisboot.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;

    private final EmployeeMapper employeeMapper;

    public List<Company> getAll(){
        List<Company> companies = companyMapper.getAll();
        return companies.stream()
                .map((company -> {
                    company.setEmployeeList(employeeMapper.getByCompanyId(company.getId()));
                    return company;
                })).collect(Collectors.toList());
    }

}
