package me.hyunsoo.aboutmybatisboot.mapper;

import me.hyunsoo.aboutmybatisboot.beans.Company;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface CompanyMapper {
    //id는 입력해 줄 필요가 없음
    //입력 된 데이터 개수가 반환이 됩니다

    @Insert("INSERT INTO COMPANY(COMPANY_NAME, COMPANY_ADDRESS) VALUES(#{company.name},#{company.address})")
    @Options(useGeneratedKeys=true, keyProperty="id") //생성된 키를 사용하는 것 OK, 걔를 id에 꽂아주세
    int insert(@Param("company")Company company);

    //--XML 설정을 활용
    int insertByXml(Company company);


    //comlumn명을 dto와 맞춰주기 위해 @Result를 활용하면 좋습니다.
    @Select("SELECT * FROM COMPANY")
    @Results(id="CompanyMap", value = {
            @Result(column = "company_name", property = "name"),
            @Result(column = "company_address", property = "address"),
            @Result(column = "id", property = "employeeList", many = @Many(select = "me.hyunsoo.aboutmybatisboot.mapper.EmployeeMapper.getByCompanyId"))
    })
    List<Company> getAll();

    //--XML 설정을 활용
    List<Company> getAllByXml();


    @Select("SELECT * FROM COMPANY WHERE id = #{id}")
    @ResultMap("CompanyMap")
    Company getById(@Param("id") int id);



}
