package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.maoyan.mapper.*;
import com.maoyan.pojo.*;
import com.maoyan.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleServlet extends HttpServlet {
          public  String    movie_Id;
          public  String   cinema_id;


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
                findScheduleByCinemaAndMovie(req, resp);
                break;
            case "findScheduleById":
                findScheduleById(req, resp);
                break;
            case "selectByUserid":
                selectByUserid(req,resp);
                break;
            case "selectSchedule":
                selectSchedule(req, resp);
                break;

        }

    }


    /***
     *    获取电影id和影院id
     *
     * **/

    public void findScheduleByCinemaAndMovie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         movie_Id = req.getParameter("movie_id");
         cinema_id = req.getParameter("cinema_id");

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();
        ScheduleMapper mapper = sqlSession.getMapper(ScheduleMapper.class);
        CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
        MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
        List<Map<String, Object>> schedule = mapper.selectByid(movie_Id, cinema_id);
        List<Cinema> cinemaList = cinemaMapper.selectByCinemaid(cinema_id);
        List<Movie> movies = movieMapper.selectByID(movie_Id);

         sqlSession.close();

        Map map = new HashMap<>();
        map.put("schedulebymid", schedule);
        map.put("cinemaList", cinemaList);
        map.put("movies", movies);
        String scheduleList = JSON.toJSONString(map);

        out.println(scheduleList);

    }


    /***
     *    获取场次id
     *
     * */
    public void findScheduleById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String schedule_id = req.getParameter("schedule_id");


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper ordermapper = sqlSession.getMapper(OrderMapper.class);
        ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
        MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
        CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
        HallMapper hallMapper = sqlSession.getMapper(HallMapper.class);

        List<Order> orders = ordermapper.selectAll();
       List<Schedule> schedules = scheduleMapper.selectByScheduleid(schedule_id);
        List<Movie> movies = movieMapper.selectByID(movie_Id);
        List<Cinema> cinemas = cinemaMapper.selectByCinemaid(cinema_id);
       List<Hall> halls = hallMapper.selectbyHallname(schedule_id);

        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        sqlSession.close();

        Map map = new HashMap<>();
        map.put("orderlist", orders);
        map.put("scheduleslist",schedules);
        map.put("movieslist",movies);
        map.put("cinemaList",cinemas);
        map.put("hallList",halls);

        String orderlist = JSON.toJSONString(map);
        out.println(orderlist);

    }


    /**
     *  查询用户是否购买过此电影
     * @selectByUserid
     * **/
    public void selectByUserid(HttpServletRequest req,HttpServletResponse resp)throws IOException{

        String user_id = req.getParameter("user_id");


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
        List<Schedule> scheduleList = scheduleMapper.selectByUserid(user_id);

        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        sqlSession.close();

        Map map = new HashMap<>();
        map.put("scheduleList", scheduleList);

        String orderlist = JSON.toJSONString(map);
        out.println(orderlist);


    }


    /*
     * 查询电影场次
    *  @selectSchedule
    * */
    public void selectSchedule(HttpServletRequest req,HttpServletResponse resp)throws IOException{


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
        List<Map> schedule = scheduleMapper.selectSchedule();
        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        sqlSession.close();

        Map map = new HashMap<>();
       map.put("schedule", schedule);

        String orderlist = JSON.toJSONString(map);
        out.println(orderlist);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
