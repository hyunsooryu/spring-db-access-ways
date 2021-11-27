package me.hyunsoo.aboutmybatis.user;

import me.hyunsoo.aboutmybatis.User;

@MapperTarget
public interface UserMapper2 {
    User selectUserById(int id);
}
