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
import java.text.ParseException;
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
            case "buyTickets":
                try {
                    buyTickets(req, resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "findOrderByUserName":
                findOrderByUserName(req, resp);
                break;

        }
    }

/***
 * 用户结算提交订单
 * */
    public void buyTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        //生成当前时间戳，生成用户订单
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String order_id = df.format(date);   //生成订单id
        Date order_time = df.parse(order_id);   //生成订单时间
        long scheduleId =  Long.parseLong(req.getParameter("schedule_id")) ;
        String[] position = req.getParameterValues("position");
        int price = Integer.parseInt(req.getParameter("price")) ;
        long user_id =  Long.parseLong(req.getParameter("user_id")) ;
        int order_state = 1;

        Order order = new Order();
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            order.setOrder_id(order_id);
            order.setUser_id(user_id);
            order.setSchedule_id(scheduleId);
            order.setOrder_position(position[i]);
            order.setOrder_state(order_state);
            order.setOrder_price(price);
            order.setOrder_time(order_time);
            orderList.add(order);
        }

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
         //执行添加方法
         int i = ordermapper.insertOrder(orderList);

         Map map = new HashMap<>();
         if(i>0){
             map.put("code",0);
         }else {
             map.put("code",1);
         }
        sqlSession.commit();
         String msg = JSON.toJSONString(map);
         resp.getWriter().println(msg);

    }



    /****
     *  查找用户订单信息
     *
     * */
         public  void findOrderByUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

             String user_id = req.getParameter("user_id");

             //调用mybatis工具类
             SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

             //获取sqlSession对象
             SqlSession sqlSession = sqlSessionFactory.openSession();
             OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
             List<Order> orders = ordermapper.selectByid(user_id);


             resp.setContentType("application/json;charset=utf-8"); //返回json格式
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
