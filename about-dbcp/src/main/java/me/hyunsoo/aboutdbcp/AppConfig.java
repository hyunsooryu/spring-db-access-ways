package me.hyunsoo.aboutdbcp;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

   @Bean
    DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        String username = "hyunsooryu";
        String password = "pass";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/springboot?useSSL=false";
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setMaxIdle(8);
        dataSource.setMinIdle(8);
        dataSource.setInitialSize(8);
        dataSource.setMaxTotal(8);
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
       JdbcTemplate jdbcTemplate = new JdbcTemplate();
       jdbcTemplate.setQueryTimeout(3000);
       jdbcTemplate.setDataSource(dataSource);
       return jdbcTemplate;
    }

}
