<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="../static/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="icon" type="image/x-icon" href="../static/images/logo.ico"/>
    <link rel="stylesheet" type="text/css" href="../static/css/header.css">
    <script src="../static/js/Api.js"></script>
    <script src="../static/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css" media="all">
    <title>猫眼电影</title>
</head>
<body>
 	<!-- 导航栏 -->
     <div class="header navbar navbar-fixed-top">
        <div class="header-top">
            <div class="header-inner">
                <h1 >
                    <a href="javascript:void(0)"  id="logo" ></a>
                </h1>
                <div class="nav">
                    <ul>
                        <li><a href="./mainPage.jsp">首页</a></li>
                        <li class="active"><a href="./movieList.jsp">电影</a></li>
                        <li><a href="javascript:void(0)">影院</a></li>
                        <li><a href="javascript:void(0)">榜单</a></li>
                    </ul>
                </div>
                <div class="app-download">
                </div>
                <div class="user-info">
                <div class="user-avatar J-login">
                    <ul class="layui-nav" style="background-color: #fff;">
                        <li class="layui-nav-item header-li" style="width:40px;" lay-unselect="" style="width: 40px;">

                        </li>
                    </ul>
                </div>
                </div>

                <form action="">
                    <input name="searchMovie" class="search" type="search" maxlength="32" placeholder="找影视剧、影人、影院" autocomplete="off">
                <input class="submit" type="submit" value="">
                </form>
            </div>
        </div>
    </div>


<script>

    var clientHeight = document.documentElement.clientHeight;
    $(function (){
        initHeader()
    })

    //初始化
    function initHeader(){
        var LayuiNavMore = $(".header-li");
        var user_json =  JSON.parse(localStorage.getItem("user_json"));


        layui.use('element', function(){
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
            //监听导航点击
            element.on('nav(demo)', function(elem){

                layer.msg(elem.text());
            });
        });
        if(user_json == null){
            LayuiNavMore.append(
                "<a href=\"javascript:;\" style=\"padding: 0;height: 42px; width: 42px;\"><img src=\"../static/images/head.jpg\" class=\"layui-nav-img\"></a>" +
                "<dl class=\"layui-nav-child nav-image\">" +
                "<dd><a href=\"./login.jsp\">登录</a></dd>" +
                "</dl>"
            );
        }
        else{
            var HeadImg = "";
            if(user_json.user_headImg == null || typeof user_json.user_headImg == "undefined"){
                HeadImg = "../upload/head/demo.jpg";
            }else{
                HeadImg = user_json.user_headImg;
            }
            LayuiNavMore.append(
                "<a href=\"javascript:;\" style=\"padding: 0;height: 42px; width: 42px;\"><img src=\"" + HeadImg + "\" class=\"layui-nav-img\"></a>" +
                "<dl class=\"layui-nav-child nav-image\">" +
                "<dd><a href=\"./center.jsp\" onclick=\"mycenter()\">我的订单</a></dd>" +
                "<hr/>" +
                "<dd><a href=\"./center.jsp\" onclick=\"myinformation()\">基本信息</a></dd>" +
                "<hr/>" +
                "<dd><a onclick=\"ReLogin()\" style=\"text-decoration: none; cursor: pointer;\">注销</a></dd>" +
                "<hr/>" +
                "</dl>"
            );

        }

    }
    function mycenter(){
        localStorage.setItem("usercardId",0);
    }
    function myinformation(){
        localStorage.setItem("usercardId",1);
    }
    //注销
    function ReLogin(){
        layui.use(['layer'], function(){
            var layer = layui.layer;
            layer.alert('确认要注销吗？',{icon: 0,offset: clientHeight/5},
                function (){
                    $.ajax({
                        type:'post',
                        url: url + "/user/logout",
                        dataType:'json',
                        data: {},
                        success:function (obj) {
                            window.localStorage.clear()
                            layer.closeAll();
                            window.location.href = "./mainPage.jsp";
                        }
                    });
                }
            );
        });
    }


</script>

</body>
</html>