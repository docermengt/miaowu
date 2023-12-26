package com.maoyan.mapper;

import com.maoyan.pojo.Admin;
import com.maoyan.pojo.Manage;
import com.maoyan.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManageMapper {
    //用户添加模块
    boolean addUser(@Param("uid") String uid, @Param("username")String username, @Param("password") String password , @Param("user_email")String user_email);
    //根据username查询
    List<User> selectUserName (@Param("username") String username);

    //查询所有
    List<User> selectAll();

    //根据id删除用户
    int delectUserById(@Param("user_id") String user_id);
    //修改用户密码和邮箱
    int updataPwdandemail( @Param("user_pwd")String user_pwd ,@Param("user_email")String user_email ,@Param("user_id") String user_id);

    ///*
    // 电影管理模块
    // */
    int updateMovies (String movie_id);

    /*
    *场次管理模块
    *
    * */
    //查询所有场次(多表)
    List<Map> selectAllByManage(@Param("schedule_state")String schedule_state);

    /*
    * 根据电影名查询场次
    * */
    List<Map> selectMovieByName (@Param("movie_cn_name") String movie_cn_name);

    /**
     *  评论模块
     * **/
    List<Map> selectcomment(@Param("username") String username);

    int delectCommentById(@Param("comment_id") String comment_id);


//    修改评论内容
    int updateComment( @Param("comment_id")String comment_id ,@Param("comment_content")String comment_content);

    //管理员登录
     Admin selectByAdmin(@Param("admin_uname")String admin_uname,@Param("admin_upwd")String admin_upwd);

}
