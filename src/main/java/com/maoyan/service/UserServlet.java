package com.maoyan.service;

import com.maoyan.mapper.UserMapper;
import com.maoyan.pojo.User;
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
import java.util.Date;

/***
 *  用户表controller
 *
 * */

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //获取请求url
        String path = req.getRequestURI();
        //获取最后一个斜杠下标
        int index = path.lastIndexOf("/");
        //获取请求名称
        String realPath = path.substring(index+1);

        switch (realPath){
            case "login":
                login(req, resp);
                break;
            case "register":
                register(req, resp);
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
      String  username = req.getParameter("username");
      String password = req.getParameter("password");


      //调用mybatis工具类
      SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

      //获取sqlSession对象
      SqlSession sqlSession = sqlSessionFactory.openSession();

      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      User select = mapper.select(username, password);
      if(select!=null){
          req.getSession().setAttribute("username", username);
          resp.sendRedirect("/movie/jsp/mainPage.jsp");
       }else {
          String erro = "账号或密码错误";

          req.getSession().setAttribute("l", erro);
          resp.sendRedirect("/movie/jsp/login.jsp");
      }

  }

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





