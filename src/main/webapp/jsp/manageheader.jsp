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
<%--    <link rel="stylesheet" href="../static/layui/css/layui.css" media="all">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/layui/2.9.2/css/layui.css" integrity="sha512-V8POzDh/+/NrceHV1dsdK9v6VWgQAtPaxYvQWGID2+PRoWJrjFiqlb26gE2PzdE8GIFoBvOOBtMH/SiAvj8uWQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>猫眼电影</title>
</head>
<body>
 	<!-- 导航栏 -->
     <div class="header navbar navbar-fixed-top">
        <div class="header-top">
            <div class="header-inner">
                <h1 >
                    <a href="javascript:void(0)"  id="logo"  >
                    </a>
                </h1>
                <div class="nav">

                    <ul>
<%--                        <li><a href="./mainPage.jsp">首页</a></li>--%>
<%--                        <li class="active"><a href="./movieList.jsp">电影</a></li>--%>
<%--                        <li><a href="javascript:void(0)">影院</a></li>--%>
<%--                        <li><a href="javascript:void(0)">榜单</a></li>--%>
                    </ul>
                </div>
<%--                <div class="app-download">--%>
<%--                </div>--%>
                <div class="user-info">
                <div class="user-avatar J-login">
                    <ul class="layui-nav" style="background-color: #fff;">
                        <li class="layui-nav-item header-li" style="width:40px;" lay-unselect="" style="width: 40px;">

                        </li>
                    </ul>
                </div>
                </div>


            </div>
        </div>
    </div>


<script>

    var clientHeight = document.documentElement.clientHeight;
    var admin_json =  JSON.parse(localStorage.getItem("admin_json"));
    $(function (){
            initHeader()

    })

    //初始化
    function initHeader(){
        var LayuiNavMore = $(".header-li");


        layui.use('element', function(){
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
            //监听导航点击
            element.on('nav(demo)', function(elem){
                layer.msg(elem.text());
            });
        });

            var HeadImg = "";
            if(admin_json.user_headImg == null || typeof admin_json.user_headImg == "undefined"){
                HeadImg = "../upload/head/admin.jpg";
            }else{
                HeadImg = admin_json.user_headImg;
            }
            LayuiNavMore.append(
                "<a href=\"javascript:;\" style=\"padding: 0;height: 42px; width: 42px;\"><img src=\"" + HeadImg + "\" class=\"layui-nav-img\"></a>" +
                "<dl class=\"layui-nav-child nav-image\">" +
                "<dd><a onclick=\"ReLogin()\" style=\"text-decoration: none; cursor: pointer;\">注销</a></dd>" +
                "<hr/>" +
                "</dl>"
            );

    }

    //注销
    function ReLogin(){
        layui.use(['layer'], function(){
            var layer = layui.layer;
            layer.alert('确认要注销吗？',{icon: 0,offset: clientHeight/5},
                function (){
                    $.ajax({
                        type:'post',
                        url: url + "/manage/logout",
                        dataType:'json',
                        data: {},
                        success:function () {

                            window.localStorage.removeItem("admin_json")
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