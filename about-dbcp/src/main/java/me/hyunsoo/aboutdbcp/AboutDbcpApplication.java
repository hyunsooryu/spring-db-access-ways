package me.hyunsoo.aboutdbcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class AboutDbcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutDbcpApplication.class, args);
    }

}
