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
            case "updataOrder":
                updataOrder(req, resp);
                break;
            case "findAllOrdersPage":
                findAllOrdersPage(req, resp);
                break;
            case "findAllRefundOrder":
                findAllRefundOrder(req, resp);
                break;
            case "updateOrder":
                updateOrder(req, resp);
                break;
            case "selectorder":
                selectorder(req,resp);
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
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String order_id = df2.format(date);  //生成订单id
        String time = df.format(date);
        Date order_time = df.parse(time);   //生成订单时间
        long scheduleId =  Long.parseLong(req.getParameter("schedule_id")) ;
        String[] position = req.getParameterValues("position");

        int price = Integer.parseInt(req.getParameter("price")) ;
        long user_id =  Long.parseLong(req.getParameter("user_id")) ;
        int order_state = 1;

        Order order = new Order();
            order.setOrder_id(order_id);
            order.setUser_id(user_id);
            order.setSchedule_id(scheduleId);
            order.setOrder_position(position[0]);
            order.setOrder_state(order_state);
            order.setOrder_price(price);
            order.setOrder_time(order_time);


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
         //执行添加方法
         int i = ordermapper.insertOrder(order);

         Map map = new HashMap<>();
         if(i>0){
             map.put("code",0);
         }else {
             map.put("code",1);
         }
        sqlSession.commit();
         String msg = JSON.toJSONString(map);
         resp.getWriter().println(msg);
         sqlSession.close();
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
            sqlSession.close();
         }



         /*申请退票
         *   updataOrder
         * */
         public  void updataOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

             String order_id = req.getParameter("order_id");
             resp.setContentType("application/json;charset=utf-8"); //返回json格式
             PrintWriter writer = resp.getWriter();
             //调用mybatis工具类
             SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

             //获取sqlSession对象
             SqlSession sqlSession = sqlSessionFactory.openSession();
             OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
             int i = ordermapper.updataOrder(order_id);
             Map map = new HashMap<>();
             if(i>0){
                 sqlSession.commit();
                 map.put("code", 0);
                 writer.println( JSON.toJSONString(map));
             }

             sqlSession.close();
         }

    /**
     * 订单模块
     */
    public void findAllOrdersPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlsession对象
        String title = req.getParameter("title");
        String page = req.getParameter("page");
        String state = req.getParameter("state");

        SqlSession session = sqlSessionFactory.openSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Map<String, Object>> order = mapper.findAllOrdersPage();
        System.out.println(order);
        Map map = new HashMap();
        if (order != null) {
            map.put("data", order);
            map.put("code", 0);
            map.put("count", order.size());
            resp.getWriter().write(JSON.toJSONString(map));
        }

        session.close();
    }


    //查看退票订单
    public void findAllRefundOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlsession对象
        String title = req.getParameter("title");
        String page = req.getParameter("page");
        String state = req.getParameter("state");

        SqlSession session = sqlSessionFactory.openSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Map<String, Object>> order = mapper.findAllRefundOrder();
        System.out.println(order);
        Map map = new HashMap();
        if (order != null) {
            map.put("data", order);
            map.put("code", 0);
            map.put("count", order.size());
            resp.getWriter().write(JSON.toJSONString(map));
        }

        session.close();
    }

    //删除订单
    public void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String order_id = req.getParameter("order_id");
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int i = mapper.updateOrder(order_id);
        if (i > 0) {
            Map map = new HashMap();
            map.put("code", 0);
            map.put("msg", "退票成功！");
            sqlSession.commit();
            sqlSession.close();
            String msg = JSON.toJSONString(map);
            resp.getWriter().println(msg);

        }
    }
    public void selectorder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("text/json;charset=utf-8");
        resp.setContentType("text/json;charset=utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlsession对象
        String title = req.getParameter("user_name");
//        String page = req.getParameter("page");
        System.out.println(title);
        SqlSession session = sqlSessionFactory.openSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
//        List<User> users = mapper.selectUserID(title);
        List<Map> users = mapper.selectorder(title);
        System.out.println(users);
        Map map = new HashMap();
        if (users != null) {
            map.put("data", users);
            map.put("code", 0);
            map.put("count", users.size());
            resp.getWriter().write(JSON.toJSONString(map));
        }

        session.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
