package com.maoyan.mapper;

import com.maoyan.pojo.Cinema;
import com.maoyan.pojo.Hall;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CinemaMapper {

    //查询影院
    List<Cinema> selectCinema();

    //id查询
    List<Cinema> selectByCinemaid(@Param("cinema_id") String cinema_id);

}
