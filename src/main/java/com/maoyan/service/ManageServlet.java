package com.maoyan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maoyan.mapper.ManageMapper;
import com.maoyan.mapper.UserMapper;
import com.maoyan.pojo.Admin;
import com.maoyan.pojo.Manage;
import com.maoyan.pojo.User;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求url
        String path = req.getRequestURI();
        String realPath = GetPathUrl.getUrl(path);

        switch (realPath) {
            case "findAllUser":
                findAllUser(req, resp);
                break;
            case "selectUserName":
                selectUserName(req, resp);
                break;
            case "addUser":
                addUser(req, resp);
                break;
            case "delectMovies":
                delectMovies(req, resp);
                break;
            case "selectAllByManage":
                selectAllByManage(req, resp);
                break;
            case "selectMovieByName":
                selectMovieByName(req, resp);
                break;
            case "delectUserById":
                delectUserById(req, resp);
                break;
            case "updataPwdandemail":
                updataPwdandemail(req, resp);
                break;

            case "adminlogin":
                adminlogin(req, resp);
                break;
            case "adminlogout":
                adminlogout(req, resp);
                break;
            case "updateComment":
                updateComment(req, resp);
                break;
            case "deleteComemnt":
                deleteComemnt(req, resp);
                break;
            case "findComment":
                findComment(req, resp);
                break;


        }


    }



    //根据名字查询用户
    public void selectUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        resp.setContentType("text/json;charset=utf-8");
        //获取sqlsession对象
        String title = req.getParameter("title");
        System.out.println(title);
        sqlSession = sqlSessionFactory.openSession();
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        List<User> users = mapper.selectUserName(title);
        Map map = new HashMap();
        if (users != null) {

            map.put("data", users);
            map.put("code", 0);
            map.put("count", users.size());
            resp.getWriter().write(JSON.toJSONString(map));
        }

        sqlSession.close();
    }

    //查询所有用户
    public void findAllUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        String page = req.getParameter("page");
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        List<User> users = mapper.selectAll();
        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        Map map = new HashMap();
        map.put("data", users);
        map.put("code", 1);
        map.put("count", users.size());
        String userlist = JSON.toJSONString(map);

        resp.getWriter().println(userlist);
        sqlSession.close();
    }

    /*
     *  添加用户
     * register
     * */
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String user_name = req.getParameter("user_name");
        String user_pwd = req.getParameter("user_pwd");
        String user_email = req.getParameter("user_email");

        //生成当前时间戳，生成用户id
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String uid = df.format(date);//获取String类型的时间
        sqlSession = sqlSessionFactory.openSession();
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);

        boolean user = mapper.addUser(uid, user_name, user_pwd, user_email);
        sqlSession.commit();
        sqlSession.close();
        PrintWriter writer = resp.getWriter();
        String msg = JSON.toJSONString("添加成功！");
        writer.println(msg);

    }

    /*
     *
     * 下架电影方法
     * */
    public void delectMovies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String movie_id = req.getParameter("movie_id");
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        int i = mapper.updateMovies(movie_id);
        if (i > 0) {
            Map map = new HashMap();
            map.put("code", 0);
            sqlSession.commit();
            sqlSession.close();
            String msg = JSON.toJSONString(map);
            resp.getWriter().println(msg);

        }
    }

    //修改用户密码和邮箱
    public void updataPwdandemail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF-8");
        String user_email = req.getParameter("user_email");
        String user_id = req.getParameter("user_id");
        String user_pwd = req.getParameter("user_pwd");

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
        int i = manageMapper.updataPwdandemail(user_pwd, user_email, user_id);
        sqlSession.commit();
//        System.out.println(i);
        sqlSession.close();
        Map map = new HashMap<>();


        PrintWriter writer = resp.getWriter();
        String msg = JSONObject.toJSONString("修改成功！");
        writer.println(msg);

//
//        String msg = JSONObject.toJSONString(map);
//        resp.getWriter().println(msg);
//
//        PrintWriter writer = resp.getWriter();
//        String msg = JSON.toJSONString("添加成功！");
//        writer.println(msg);

    }

    //查询所有场次
    public void selectAllByManage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String schedule_state = req.getParameter("schedule_state");


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        List<Map> manages = mapper.selectAllByManage(schedule_state);
        resp.setContentType("text/json;charset=utf-8"); //返回json格式
        Map map = new HashMap();
        map.put("data", manages);
        map.put("code", 0);
        map.put("count", manages.size());
        String userlist = JSON.toJSONString(map);

        resp.getWriter().println(userlist);
        sqlSession.close();
    }

    //根据电影名查询用户
    public void selectMovieByName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        resp.setContentType("text/json;charset=utf-8");
        req.getMethod();
        String title = req.getParameter("title");
        System.out.println(title);
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        List<Map> manages = mapper.selectMovieByName(title);
        Map map = new HashMap();
        if (manages != null) {
            map.put("data", manages);
            map.put("code", 0);
            map.put("count", manages.size());
            resp.getWriter().write(JSON.toJSONString(map));
        }

        sqlSession.close();
    }

    public void delectUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String user_id = req.getParameter("user_id");
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        int i = mapper.delectUserById(user_id);
        if (i > 0) {
            Map map = new HashMap();
            map.put("code", 0);
            sqlSession.commit();
            sqlSession.close();
            String msg = JSON.toJSONString(map);
            resp.getWriter().println(msg);

        }
    }


    /*
     * 主任的
     * */


    /**
     * 评论模块
     */

    public void findComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        req.setCharacterEncoding("text/json;charset=utf-8");
        resp.setContentType("text/json;charset=utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlsession对象
        String title = req.getParameter("user_name");
//        String page = req.getParameter("page");
        System.out.println(title);
        SqlSession session = sqlSessionFactory.openSession();
        ManageMapper mapper = session.getMapper(ManageMapper.class);
//        List<User> users = mapper.selectUserID(title);
        List<Map> users = mapper.selectcomment(title);
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


    //    修改评论
    public void updateComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF-8");
        String comment_id = req.getParameter("comment_id");
        String comment_content = req.getParameter("comment_content");

        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ManageMapper manageMapper = sqlSession.getMapper(ManageMapper.class);
        int i = manageMapper.updateComment(comment_id, comment_content);

        sqlSession.commit();
//        System.out.println(i);
        sqlSession.close();
        Map map = new HashMap<>();


        PrintWriter writer = resp.getWriter();
        String msg = JSONObject.toJSONString("修改成功！");
        writer.println(msg);
    }

    public void deleteComemnt(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String comment_id = req.getParameter("comment_id");
        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        int i = mapper.delectCommentById(comment_id);
        if (i > 0) {
            Map map = new HashMap();
            map.put("code", 0);
            sqlSession.commit();
            sqlSession.close();
            String msg = JSON.toJSONString(map);
            resp.getWriter().println(msg);

        }
    }

    //管理登录接口
    public void adminlogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String admin_uname = req.getParameter("admin_uname");
        String admin_upwd = req.getParameter("admin_upwd");


        //调用mybatis工具类
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManageMapper mapper = sqlSession.getMapper(ManageMapper.class);
        Admin admin = mapper.selectByAdmin(admin_uname, admin_upwd);
        resp.setContentType("text/json;charset=utf-8"); //返回json格式

        PrintWriter writer = resp.getWriter();
        Map map = new HashMap<>();


        if (admin != null) {
            int admin_uid = admin.getAdmin_uid();
            map.put("data", admin_uid);
            map.put("msg", "ok");

        } else {

            map.put("msg", 0);

        }
        String user = JSON.toJSONString(map);
        writer.println(user);
        sqlSession.close();
    }

    //管理员退出
    public void adminlogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8"); //返回json格式

        PrintWriter writer = resp.getWriter();
        Map map = new HashMap<>();

        map.put("msg", "ok");

        String user = JSON.toJSONString(map);
        writer.println(user);

    }

}

