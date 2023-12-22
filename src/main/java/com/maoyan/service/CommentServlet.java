package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.maoyan.mapper.CommentMapper;
import com.maoyan.pojo.Comment;
import com.maoyan.util.GetPathUrl;
import com.maoyan.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论servlet
 **/
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求url
        String path = req.getRequestURI();
        String realPath = GetPathUrl.getUrl(path);

        switch (realPath) {
            case "selectComment":
                selectComment(req, resp);
                break;
            case "addCommentByUser":
                addCommentByUser(req, resp);
                break;

        }
    }

    /**
     * 展示评论
     *
     * @selectComment
     **/

    public void selectComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String movie_id = req.getParameter("movie_id");

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("text/json;charset=utf-8"); //返回json格式


        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        List<Comment> comments = mapper.selectAll(movie_id);
        Map map = new HashMap<>();
        map.put("commentlist", comments);

        String comment = JSON.toJSONString(map);
        resp.getWriter().println(comment);
        sqlSession.close();
    }

    /**
     * 添加评论
     *
     * @addCommentByUser
     **/
    public void addCommentByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String movie_id = req.getParameter("movie_id");
        String comment_content = req.getParameter("comment_content");
        String user_id = req.getParameter("user_id");

        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String comment_time = df.format(date);//获取String类型的时间

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        resp.setContentType("text/json;charset=utf-8"); //返回json格式


        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        int i = mapper.insterComment(user_id, comment_content, movie_id, comment_time);
        sqlSession.commit();
        sqlSession.close();
        Map map = new HashMap<>();
        if (i > 0) {
            map.put("code", 0);
        } else {
            map.put("code", "erro");
        }

        String comment = JSON.toJSONString(map);
        resp.getWriter().println(comment);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
