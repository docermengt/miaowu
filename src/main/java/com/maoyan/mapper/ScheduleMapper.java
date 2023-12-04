package com.maoyan.mapper;

import com.maoyan.pojo.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleMapper {
    List<Schedule> selectAll();

    List<Map<String, Object>> selectByid(@Param("movie_id")String movie_id, @Param("cinema_id") String cinema_id);

 List<Schedule> selectByScheduleid(@Param("schedule_id") String schedule_id);
}
