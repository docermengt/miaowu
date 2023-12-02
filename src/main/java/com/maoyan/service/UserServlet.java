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
            case "register":
                register(req, resp);

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
      sqlSession.close();
      User select = mapper.select(username, password);
      if(select!=null){
          req.getSession().setAttribute("username", username);
          resp.sendRedirect("/movie/jsp/mainPage.jsp");
       }

  }

public  void register(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
      req.setCharacterEncoding("utf-8");
    String  username = req.getParameter("username");
    String password = req.getParameter("password");

    System.out.println(username+password);


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
         System.out.println("注册成功");
     }
    }else {
        System.out.println("用户名已存在");
    }

}





}
