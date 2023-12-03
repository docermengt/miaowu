package com.maoyan.mapper;

import com.maoyan.pojo.Movie;

import java.util.List;

public interface MovieMapper {

    //查询全部
  List<Movie>  selectAll();
  //查询票房前4的电影
  List<Movie> selectOrderby();
  //查询即将上映的电影
    List<Movie> selectBystate();
}
