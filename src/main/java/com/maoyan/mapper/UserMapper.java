package com.maoyan.mapper;

import com.maoyan.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //用户登录方法
    User select (@Param("username") String username, @Param("password") String password);
    //用户注册方法
    User selectByUserName( @Param("username")String username);}
