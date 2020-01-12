package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Insert("insert into user (name, account, token, gmt_create, gmt_modify, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModify}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modify = #{gmtModify}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
