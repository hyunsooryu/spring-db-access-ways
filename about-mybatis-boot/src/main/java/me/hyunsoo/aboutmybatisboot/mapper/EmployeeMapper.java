package me.hyunsoo.aboutmybatisboot.mapper;

import me.hyunsoo.aboutmybatisboot.beans.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Insert(
            "INSERT INTO EMPLOYEE(EMPLOYEE_NAME, EMPLOYEE_ADDRESS, COMPANY_ID)" +
            "VALUES(#{employee.name}, #{employee.address}, #{employee.companyId})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("employee")Employee employee);

    @Select("SELECT * FROM EMPLOYEE")
    @Results(
            id = "EmployeeMap",
            value =
            {
                    @Result(column = "EMPLOYEE_NAME", property = "name"),
                    @Result(column = "EMPLOYEE_ADDRESS", property = "address"),
                    @Result(column = "COMPANY_ID", property = "companyId")
            }
    )
    List<Employee> getAll();

    @Select("SELECT * FROM EMPLOYEE WHERE ID = #{id}")
    @ResultMap("EmployeeMap")
    Employee getById(@Param("id") int id);

    @Select("SELECT * FROM EMPLOYEE WHERE COMPANY_ID = #{companyId}")
    @ResultMap("EmployeeMap")
    List<Employee> getByCompanyId(@Param("companyId") int companyId);


}
