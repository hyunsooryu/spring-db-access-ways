package me.hyunsoo.aboutinmemorydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.util.List;

@Profile("prod")
@Component
public class H2Runner implements ApplicationRunner{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /**
        try(Connection connection = dataSource.getConnection()) {
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (ID))";
            statement.executeUpdate(sql);
        }
         **/

        System.out.println("========Default로 설정 된 H2 인메모리 데이터베이스 정보확인===========");
        try(Connection connection = dataSource.getConnection()){
             DatabaseMetaData metaData = connection.getMetaData();
             System.out.println("dbUrl : " + metaData.getURL());
             System.out.println("dbUsername : " + metaData.getUserName());
             System.out.println("dbDriverName : " + metaData.getDriverName());
        }
        System.out.println("============================================================");

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
