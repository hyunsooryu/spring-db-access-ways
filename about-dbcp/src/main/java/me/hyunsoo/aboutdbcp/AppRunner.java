package me.hyunsoo.aboutdbcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===========================");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean(JdbcTemplate.class);
        DataSource dataSource = (DataSource) applicationContext.getBean(DataSource.class);

        System.out.println(jdbcTemplate);
        System.out.println(dataSource);
        System.out.println("===========================");
    }
}
