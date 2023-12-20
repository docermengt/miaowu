package com.maoyan.mapper;

import com.maoyan.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    //展示评论
    List<Comment>  selectAll(@Param("movie_id")String movie_id);
    //添加评论
    int insterComment(@Param("user_id")String user_id,@Param("comment_content")String comment_content,@Param("movie_id")String movie_id,
    @Param("comment_time")String comment_time);


}
