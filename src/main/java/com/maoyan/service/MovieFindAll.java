package com.maoyan.service;

import com.maoyan.mapper.MovieMapper;
import com.maoyan.mapper.UserMapper;
import com.maoyan.pojo.Movie;
import com.maoyan.pojo.User;
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
import java.util.List;

@WebServlet("/findAllMovies")
public class MovieFindAll extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("application/json;charset=utf-8"); //返回json格式
        PrintWriter out = resp.getWriter();

        MovieMapper mapper = sqlSession.getMapper(MovieMapper.class);
        List<Movie> movies =   mapper.selectAll();
        for (Movie m: movies) {
            out.println(m.getMovie_actor());
        }




    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
