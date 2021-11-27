package me.hyunsoo.aboutmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;


/**
 * MYBATIS
 *
 * 마이바티스는 SQL을 별도의 파일로 분리해서 관리하게 해주며,
 * 객체 - SQL 사이의 파라미터 Mapping 작업을 자동으로 해주기 때문에
 * 많은 인기를 얻고 있는 기술이다.
 *
 * 가장 간단한 Persistance Framework이며,
 * XML 형태로 서술된 JDBC 코드라고 생각해도 될만큼
 * JDBC의 모든 기능을 MYBATIS가 대부분 제공한다.
 *
 * MYBATIS, MYBATIS-SPRING 을 사용한 DB ACCESS ARCHITECTURE
 *
 * Service -> Repository(Mapper) -> MYBATIS 3 | MYBATIS-SPRING -> JDBC API | DATASOURCE -> JDBC DRIVER -> DB
 *
 * MYBATIS-SPRING의 주요 컴포넌트의 역할
 * 1. SqlMapConfig.xml(mybatis 설정 파) -> VO 객체의 정보를 설정
 * 2. SqlSessionFactoryBean -> MYBATIS 설정파일을 바탕으로 SqlSessionFactory를 생성한다.
 *    @Bean으로 등록해야 함.
 * 3. SqlSessionTemplate -> 핵심적인 역할을 하는 클래스로서 SQL 실행이나, 트랜잭션 관리를 실행한다.
 *    SqlSession 인터페이스를 구현하며, Thread-Safe 하다.
 *    @Bean으로 등록해야 함.
 * 4. Mapping 파일
 *    SQL문과 OR MAPPING을 설정한다.
 *
 * SqlSessionFactoryBean 을 @Bean으로 등록할 때, DataSource 정보와 MyBatis Config 파일정보, Mapping 파일
 * 정보를 함께 설정해야 한다. + SqlSessionTemplate을 @Bean으로 등록해야 한다.
 *
 *
 * SqlSession은 ThreadSafe하기 때문에, 사용하는 데 무리가 없다. OKAY
 *
 *
 */


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class AboutMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutMybatisApplication.class, args);
    }

}
