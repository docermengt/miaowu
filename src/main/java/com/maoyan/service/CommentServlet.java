package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.maoyan.mapper.CinemaMapper;
import com.maoyan.mapper.CommentMapper;
import com.maoyan.mapper.MovieMapper;
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
import java.io.PrintWriter;
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
            case "deleteComment":
                deleteComemnt(req, resp);
                break;
            case "updateComment":
                updateComment(req, resp);
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

    }

    /**
     * 添加评论
     *
     * @addCommentByUser
     **/
    public void addCommentByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    /**
     * 删除评论
     *
     * @deleteComemnt
     **/
    public void deleteComemnt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    /**
     * 修改评论
     *
     * @updateComment
     **/
    public void updateComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
