package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maoyan.mapper.CinemaMapper;
import com.maoyan.mapper.HallMapper;
import com.maoyan.mapper.MovieMapper;
import com.maoyan.mapper.ScheduleMapper;
import com.maoyan.pojo.Cinema;
import com.maoyan.pojo.Hall;
import com.maoyan.pojo.Movie;
import com.maoyan.pojo.Schedule;
import com.maoyan.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MovieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求url
        String path = req.getRequestURI();
        //获取最后一个斜杠下标
        int index = path.lastIndexOf("/");
        //获取请求名称
        String realPath = path.substring(index+1);

        switch (realPath){
            case "findAll":
                findAll(req, resp);
                break;
            case "findMovieById":
                findMovieById(req,resp);
                break;
            case "findMoviesByType":
                findMoviesByType(req,resp);
                break;
            case "addMovie":
                addMovie(req,resp);
                break;
            case "updateMovie":
                updateMovie(req,resp);
                break;
        }

    }

    /***
     *  findAll 查询全部
     *  返回json
     * */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movies =   mapper.selectAll();
        List<Movie> boxoffice = mapper.selectOrderby();
        List<Movie> upcoming = mapper.selectBystate();


        Map map = new HashMap<>();
        map.put("movies", movies);//正在热映电影
        map.put("boxoffice",boxoffice);//票房前9电影
        map.put("upcoming",upcoming);//即将上映电影

        String  movie = JSON.toJSONString(map);

        out.println(movie); //返回json格式
    }


    /***
     * findMovieById  id查询电影
     *
     * */
    public  void  findMovieById(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{

        String movieId = req.getParameter("movie_id");
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        CinemaMapper Cinemamapper = sqlSession.getMapper(CinemaMapper.class);
        HallMapper hallMapper = sqlSession.getMapper(HallMapper.class);
        ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
        List<Movie> moviesbyid =   mapper.selectByID(movieId);
        List<Cinema>  cinemaList  =  Cinemamapper.selectCinema() ;
        List<Hall> halls = hallMapper.selectHall();
        List<Schedule> scheduleList = scheduleMapper.selectAll();


        Map map = new HashMap<>();
        map.put("moviesbyid", moviesbyid);
        map.put("cinemaList", cinemaList);
        map.put("hallsList", halls);
       map.put("scheduleList", scheduleList);
        String  movie = JSON.toJSONString(map);
        out.println(movie);

    }

/***
 *      findMoviesByType   type查询电影
 *
 * */

public  void findMoviesByType(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{

    String movie_type = req.getParameter("movie_type");
    String movie_releaseDate = req.getParameter("movie_releaseDate");
    String movie_country = req.getParameter("movie_country");
    String movie_score = req.getParameter("movie_score");
    //调用mybatis工具类
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //获取sqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    resp.setContentType("text/json;charset=utf-8"); //返回json格式
    PrintWriter out = resp.getWriter();

    MovieMapper moviemapper = sqlSession.getMapper(MovieMapper.class);
    List<Movie> movies = moviemapper.selectByType(movie_type,movie_releaseDate,movie_country,movie_score);

    Map map = new HashMap<>();
    map.put("movies", movies);

    String movielist = JSONObject.toJSONString(map);

    out.println(movielist);



}

/****
 *    添加电影 addMovie
 * */
    public void addMovie(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{

    }

/**
 *   修改电影 updateMovie
 * **/
    public  void updateMovie (HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
