package me.hyunsoo.aboutmybatis;


import lombok.RequiredArgsConstructor;
import me.hyunsoo.aboutmybatis.user.UserMapper2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SqlSession sqlSession;

    private final UserMapper userMapper;

    private final UserMapper2 userMapper2;

    //@Transactional(timeout = 0)
    public User findUserById(int id){
        //namespace + queryId
        return sqlSession.selectOne("userNS.selectUserById", id);
    }


    public User findUserByIdWithUserMapper(int id){
        System.out.println("HELLO, WORLD");
        return userMapper.selectUserById(id);
    }

    public User findUserByIdWithUserMapperScanner(int id){
        System.out.println("HELLO, WORLD, I am Using By Mapper Scanner");
        return userMapper2.selectUserById(id);
    }




}
