package me.hyunsoo.aboutmybatis;

import me.hyunsoo.aboutmybatis.user.MapperTarget;
import me.hyunsoo.aboutmybatis.user.UserMapper2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;


//MY BATIS 사용을 위한 DataConfiguration

//MapperScanConfiguer 를 빈으로 등록 시 에러가 다니때문에, JavaConfig 시는 MapperScan을 활용한다.
@MapperScans(
        {
                @MapperScan(
                        basePackages = "me.hyunsoo.aboutmybatis.user",
                        annotationClass = MapperTarget.class

                )
        }
        )
@Configuration
public class DataConfig {

    @Autowired
    ResourceLoader resourceLoader;

    //DataSource
    @Bean
    DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springboot?useSSL=false");
        dataSource.setUsername("hyunsooryu");
        dataSource.setPassword("pass");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    //SqlSessionFactory -> SqlSessionFactory를 생성하기 위해서는, SqlSessionFactoryBean을 생성해야 하며,
    //이 때 필요한 조건은, MybatisConfig, Mapper, DataSource
    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        //1. mybatis config 설정
        Resource configLocation = resourceLoader.getResource("classpath:/mybatis-config.xml");
        //2. mybatis mapper 설정
        //sqlSession
        Resource mapperLocation1 = resourceLoader.getResource("classpath:/mapper/user.xml");
        //mapper
        Resource mapperLocation2 = resourceLoader.getResource("classpath:/mapper/user-mapper.xml");
        //mapper scanner
        Resource mapperLocation3 = resourceLoader.getResource("classpath:/mapper/user-mapper2.xml");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //1. mybatis - config
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        //2. mybatis - mapper
        sqlSessionFactoryBean.setMapperLocations(mapperLocation1, mapperLocation2, mapperLocation3);
        //3. dataSource
        sqlSessionFactoryBean.setDataSource(dataSource);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * SqlSession으로 주입받아 활용할 수 있는, sqlSessionTemplate
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name="sqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    /**
     * SqlSession이 아닌, MapperInterface를 활용하는 방법
     * 1. 매퍼 하나하나를 매퍼팩토리 빈을 통해 생성
     */
    @Bean
    MapperFactoryBean<UserMapper> userMapper(SqlSessionTemplate sqlSessionTemplate){
        MapperFactoryBean<UserMapper> userMapper = new MapperFactoryBean<UserMapper>();
        userMapper.setSqlSessionTemplate(sqlSessionTemplate);
        userMapper.setMapperInterface(UserMapper.class);
        return userMapper;
    }

    /**
     * SqlSession이 아닌, MapperInterface를 활용하는 방법
     * 2. 매퍼 스캔을 통해, 영역의 모든 Interface를 매퍼로 등록 후 사용
     * 아래와 같이 등록 시 에러발생
     * JavaConfiguration 사용시, @MapperScan 을 활용하도록 한다.
     * https://heartdev.wordpress.com/2013/07/04/mybatis-spring-%EC%9D%98-mapperscannerconfigurer-%EB%A5%BC-%ED%86%B5%ED%95%B4-spring-%EA%B3%B5%EB%B6%80%ED%95%98%EA%B8%B0/
     */
    /**
    @Bean
     MapperScannerConfigurer mapperScannerConfigurer(){
       MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
       mapperScannerConfigurer.setBasePackage("me.hyunsoo.aboutmybatis.user");
       return mapperScannerConfigurer;
    }
    **/

}
