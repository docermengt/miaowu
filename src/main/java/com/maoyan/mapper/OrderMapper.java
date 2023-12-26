package com.maoyan.mapper;

import com.maoyan.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

//    获取订单
    List<Order> selectAll();
    //提交订单
    int insertOrder(@Param("order") Order order);
    //查询用户订单
    List<Order> selectByid(@Param("user_id")String user_id);
    //退票订单
    int  updataOrder(@Param("order_id")String order_id);

    /*
     *
     * 主任的*/
//  **订单模块//
    List<Map<String, Object>> findAllOrdersPage();

    //显示退票订单
    List<Map<String, Object>> findAllRefundOrder();

    int updateOrder(String order_id);
    List<Map> selectorder(@Param("username") String username);

}
