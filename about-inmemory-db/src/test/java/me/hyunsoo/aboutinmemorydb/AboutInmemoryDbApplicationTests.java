package me.hyunsoo.aboutinmemorydb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AboutInmemoryDbApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println("=======H2 DB INITIALIZATION BEGIN==================");
        try(Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("drivername : " + metaData.getDriverName());
            System.out.println("usernam : " + metaData.getUserName());
            System.out.println("url : " + metaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("=======H2 DB INITIALIZATION E N D==================");

        System.out.println("CREATING TEST TABLE USER START");
        String sql = "CREATE TABLE GS27(ID INTEGER NOT NULL PRIMARY KEY auto_increment, name VARCHAR(45), work_at VARCHAR(45), speciality VARCHAR(45), age VARCHAR(45), phone_number VARCHAR(45))";
        jdbcTemplate.execute(sql);
        System.out.println("CREATING TEST TABLE USER E N D");


        System.out.println("INSERT DATA TO DB");
        jdbcTemplate.execute("INSERT INTO GS27(name, work_at, speciality, age, phone_number) VALUES('minsu', 'gsretail', 'MD', '26', '010-5757-3838')");

        List<User> user = jdbcTemplate.query("SELECT * FROM GS27", (rs, rowNum) ->{
            User tmpUser = new User();
            tmpUser.setId(rs.getInt("id"));
            tmpUser.setAge(rs.getString("age"));
            tmpUser.setName(rs.getString("name"));
            tmpUser.setSpeciality(rs.getString("speciality"));
            tmpUser.setWork_at(rs.getString("work_at"));
            return tmpUser;
        });

        System.out.println("SELECT DATA TO DB");
        user.stream().forEach(System.out::println);
    }

}
