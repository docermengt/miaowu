package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maoyan.mapper.MovieMapper;
import com.maoyan.pojo.Movie;
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
        }

    }

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("application/json;charset=utf-8"); //返回json格式
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







    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
