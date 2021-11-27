package me.hyunsoo.aboutmybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    //1. SqlSesison 활용
    @Test
    void userSelectOneTestWithSqlSession(){
        User user = userRepository.findUserById(1);
        System.out.println(user);
    }


    //2. Mapper 활용
    @Test
    void userSelectOneTestWithMapper(){
        User user = userRepository.findUserByIdWithUserMapper(1);
        System.out.println(user);
    }


    //3. MapperScanner 활용
    @Test
    void userSelectOneTestWithMapperScanner(){
        User user = userRepository.findUserByIdWithUserMapperScanner(1);
        System.out.println(user);
    }

}