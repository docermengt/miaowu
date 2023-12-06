package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maoyan.mapper.UserMapper;
import com.maoyan.pojo.User;
import com.maoyan.util.GetPathUrl;
import com.maoyan.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 *  用户表controller
 *
 * */

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求url
        String path = req.getRequestURI();
        String  realPath = GetPathUrl.getUrl(path);

        switch (realPath){
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

/**
 *   用户登录方法 login
 * */

  public  void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

      String  username = req.getParameter("user_name");
      String password = req.getParameter("user_pwd");


      //调用mybatis工具类
      SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

      //获取sqlSession对象
      SqlSession sqlSession = sqlSessionFactory.openSession();
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User select = mapper.select(username, password);
      resp.setContentType("text/json;charset=utf-8"); //返回json格式

      PrintWriter writer = resp.getWriter();
      Map map = new HashMap<>();


      if(select!=null){
          String user_Id = select.getUser_id();
          map.put("data",user_Id);

       }else {
          String erro = "账号或密码错误";
          map.put("msg","fail");
          req.getSession().setAttribute("l", erro);
      }
      String  user = JSON.toJSONString(map);
      writer.println(user);

  }


  /***
   * 用户退出方法
   * logout
   * */

  public  void logout(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

      resp.setContentType("text/json;charset=utf-8"); //返回json格式

      PrintWriter writer = resp.getWriter();
      Map map = new HashMap<>();
      map.put("exit","exit");
      String  user = JSON.toJSONString(map);
      writer.println(user);

}

/*
*  用户注册
* register
* */

public  void register(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
      req.setCharacterEncoding("utf-8");
      resp.setContentType("text/html;charset=UTF-8");
    String  username = req.getParameter("uname");
    String password = req.getParameter("pwd");



    //生成当前时间戳，生成用户id
    Date date = new Date();//获取当前的日期
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
    String uid = "my"+df.format(date);//获取String类型的时间



    //调用mybatis工具类
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //获取sqlSession对象
    SqlSession sqlSession = sqlSessionFactory.openSession();

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);

    User user = mapper.selectByUserName(username);

    if(user==null){
        int  i = mapper.insertUser(uid,username, password);
     if(i>0){
         sqlSession.commit();
         PrintWriter writer = resp.getWriter();
         writer.write("注册成功"+"<br>");
         writer.write("3秒后跳转至主页面");
         resp.setHeader("refresh","3;URL=http://localhost:8081/movie/jsp/mainPage.jsp");
        }
    }else {
        String rerro = "用户名已存在";

        req.getSession().setAttribute("r",rerro);
       resp.sendRedirect("/movie/jsp/register.jsp");
    }
    }

}





