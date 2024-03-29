<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>猫眼电影-管理员登录页面</title>
</head>
<script src="../static/layui/layui.js" charset="utf-8"></script>
<body>
<style>
    @IMPORT url("../static/css/login.css");
</style>


<header>
    <div class="logo">
        <a href="#" class="site-logo"></a>
    </div>
    <%--  <img src="../static/images/index_Client.png">--%>
</header>
<script src="../static/js/jquery-3.3.1.min.js"></script>
<script src="../static/js/Api.js"></script>

<div id="loginDiv" class="forget1">
    <form id="form">
        <h1 id="loginMsg">管理员登录</h1>
        <p>用户名:<input id="username" type="text"></p>

        <p>密 码: &nbsp; <input id="password" class="password" type="password"></p>
        <br>
        <span id="username_err" style="display: block;color: red"></span>
        <div id="subDiv">
            <input type="button" class="button" value="登录" id="button">
            <input type="reset" class="button" value="reset">&nbsp;

        </div>
    </form>
</div>

<script>
    $(function () {

        var admin_uname = $("#username")
        var admin_upwd = $("#password")



        //登录 ajax
        $('#button').click(function () {
            var name = admin_uname.val()
            var pwd = admin_upwd.val()
            $.ajax({
                type: "post",
                url: url + "/manage/adminlogin",
                data: {
                    admin_uname: name,
                    admin_upwd: pwd
                },
                dataType: "json",
                success: function (obj) {
                    if (obj.msg == 0) {
                        $('#username_err').html("账户名或密码错误")
                    } else {
                        localStorage.setItem("admin_name", name)
                        window.location.href = "./manage.jsp";
                    }
                },
                error: function () {
                    console.log("network error!");
                }
            });
        })
    })





</script>
</body>
</html>
