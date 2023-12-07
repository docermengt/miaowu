package com.maoyan.mapper;

import com.maoyan.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

//    获取订单
    List<Order> selectAll();
    //提交订单
    void insertOrder(Order order);
    //查询用户订单
    List<Order> selectByid(@Param("user_id")String user_id);

}
