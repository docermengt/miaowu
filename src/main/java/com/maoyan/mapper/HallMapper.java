package com.maoyan.mapper;

import com.maoyan.pojo.Hall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HallMapper {


    //查询大厅列表
    List<Hall> selectHall();

    List<Hall> selectbyHallname(@Param("schedule_id")String schedule_id);
}
