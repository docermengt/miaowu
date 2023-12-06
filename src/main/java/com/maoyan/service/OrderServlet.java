package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.maoyan.mapper.OrderMapper;
import com.maoyan.mapper.UserMapper;
import com.maoyan.pojo.Order;
import com.maoyan.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求url
        String path = req.getRequestURI();
        //获取最后一个斜杠下标
        int index = path.lastIndexOf("/");
        //获取请求名称
        String realPath = path.substring(index + 1);

        switch (realPath) {
            case "findScheduleByCinemaAndMovie":
                buyTickets(req, resp);
                break;
            case "findOrderByUserName":
                findOrderByUserName(req, resp);
                break;
        }
    }

/***
 * 用户结算
 * */
    public void buyTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //生成当前时间戳，生成用户id
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String orderid = df.format(date);//获取String类型的时间
        String scheduleId = req.getParameter("schedule_id");
        String[] position = req.getParameterValues("position");
        String price = req.getParameter("price");
        String userId = (String) req.getSession().getAttribute("user_id");


        Order order = new Order();
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            order.setOrder_id(orderid);
        }
        System.out.println(userId);


    }

    /****
     *  查找用户订单信息
     *
     * */
         public  void findOrderByUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

             String user_name = req.getParameter("user_name");

             //调用mybatis工具类
             SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

             //获取sqlSession对象
             SqlSession sqlSession = sqlSessionFactory.openSession();
             OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
             List<Order> orders = ordermapper.selectByid(user_name);
             resp.setContentType("text/json;charset=utf-8"); //返回json格式

             PrintWriter writer = resp.getWriter();
             Map map = new HashMap<>();

             map.put("orderlist",orders);

             String  order = JSON.toJSONString(map);

            writer.println(order);

         }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
