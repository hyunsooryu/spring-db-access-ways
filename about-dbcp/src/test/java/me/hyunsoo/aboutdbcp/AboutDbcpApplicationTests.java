package me.hyunsoo.aboutdbcp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * JDBC TEMPLATE 활용
 * DBCP 가 필요하다. -> DataSource로 등록해서 사용해야 한다.
 */

@SpringBootTest
class AboutDbcpApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void jdbcTemplateTest() {
        String query = "SELECT * FROM USER";

        jdbcTemplate.query(query,(rs, rowNum)->{
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            return user;
        }).stream().forEach(System.out::println);

    }

}
