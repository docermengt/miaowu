package com.maoyan.mapper;

import com.maoyan.pojo.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleMapper {
    List<Schedule> selectAll();

    List<Map<String, Object>> selectByid(@Param("movie_id")String movie_id, @Param("cinema_id") String cinema_id);

 List<Schedule> selectByScheduleid(@Param("schedule_id") String schedule_id);

 //查询用户是否购买过此电影
    List<Schedule>  selectByUserid(@Param("user_id")String user_id);

    //查询电影场次
    List<Map> selectSchedule();
}
