package com.lazyben.exercise.mapper;

import com.lazyben.exercise.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(@Param("id") int id);

    @Select("SELECT * FROM user WHERE username=#{username}")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(username, encrypted_password) VALUES(#{username}, #{password})")
    void createUser(@Param("username") String username, @Param("password") String password);
}
