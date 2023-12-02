package com.maoyan.mapper;

import com.maoyan.pojo.Movie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieMapper {

    //查询全部
  List<Movie>  selectAll();
}
