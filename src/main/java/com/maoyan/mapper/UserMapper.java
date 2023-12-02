package com.maoyan.mapper;

import com.maoyan.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //用户登录方法
    User select (@Param("username") String username, @Param("password") String password);
    //查询用户名
    User selectByUserName( @Param("username")String username);
    //用户注册
    int insertUser(@Param("uid") String uid,@Param("username")String username, @Param("password") String password);

}
