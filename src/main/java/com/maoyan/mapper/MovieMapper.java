package com.maoyan.mapper;

import com.maoyan.pojo.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MovieMapper {

  //查询所有电影
  List<Map> selectAllByType();
    //查询所有上映的电影
  List<Movie>  selectAll();
  //查询票房前4的电影
  List<Movie> selectOrderby();
  //查询即将上映的电影
    List<Movie> selectBystate();
    //id查询电影
    List<Movie> selectByID(@Param("movie_id")String movie_id);
    //type查询电影
  List<Movie> selectByType(@Param("movie_type") String movie_type ,@Param("movie_releaseDate") String movie_releaseDate
  ,@Param("movie_country") String movie_country,@Param("movie_score") String movie_score);
}
