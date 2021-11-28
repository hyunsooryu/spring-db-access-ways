package me.hyunsoo.aboutinmemorydb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring-Jdbc가 클래스패스에 있다면, boot 가 자동설정이 필요한 빈들을 등록시켜줍니다.
 *  1. org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 *  2. org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
 *
 *  * DataSource, JdbcTemplate AutoConfiguration에 의한 자동 Bean 설정
 *
 * Spring Boot 는 H2 의존성이 클래스패스에 들어있고, 아무런 data source 설정을 하지 않으면,
 * 기본적으로 Inmemory DB를 사용하게 띄워줍니다. 아무런 문제 없이 만들어줍니다.
 *
 * H2 콘솔을 사용하는 방법
 *     spring-boot-devtools 를 추가하거나,
 *     spring.h2.console.enabled=true만 추가
 *     /h2-console 로 접속 (이 path 도 바꿀 수 있음)
 */


@SpringBootApplication
public class AboutInmemoryDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutInmemoryDbApplication.class, args);
    }

}
