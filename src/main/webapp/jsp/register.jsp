<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>猫眼电影-注册页面</title>
</head>
<body>
<script src="../static/js/Api.js"></script>
<script src="../static/layui/layui.js"></script>
<style>
    @IMPORT url("../static/css/register.css");
</style>

<div class="form-div">
    <header>
        <div class="logo">
            <a href="#" class="site-logo"></a>
        </div>
        <img src="../static/images/index_Client.png" style="position: absolute ;left: 150px">
    </header>
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="uname" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: block"></span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="pwd" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


        </table>

        <div class="buttons">
            <input value="注 册" type="button" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
<script src="../static/js/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        var clientHeight = document.documentElement.clientHeight;
        var username = $("#username")
        var userpwd = $("#password")
        var reg = /^[a-zA-Z]\w{7,17}$/
        $('#reg_btn').click(function () {

            var user_name = username.val()
            var user_pwd = userpwd.val()

            if (user_name == "" || user_pwd == "" || user_name == null || user_pwd == null) {
                layui.use(['layer'], function () {
                    var layer = layui.layer;
                    layer.alert('用户名和密码不能为空', {icon: 0, offset: clientHeight / 5});
                });

            }
          else  if (!reg.test(user_pwd)) {
                layui.use(['layer'], function () {
                    var layer = layui.layer;
                    layer.alert('密码以字母开头包含字母、数字,长度至少8位', {icon: 0, offset: clientHeight / 5});
                });
            }
            else {

                    $.ajax({
                        type: "post",
                        url: url + "/user/register",
                        data: {
                            user_name: user_name,
                            user_pwd: user_pwd
                        },
                        dataType: "json",
                        success: function (obj) {
                            if (obj.msg == 0) {
                                layui.use(['layer'], function () {
                                    var layer = layui.layer;
                                    layer.alert('用户名已存在', {icon: 0, offset: clientHeight / 5});
                                });
                            } else {
                                layui.use(['layer'], function () {
                                    var layer = layui.layer;
                                    layer.alert('注册成功', {icon: 0, offset: clientHeight / 5},
                                        function () {
                                            localStorage.setItem("user_name", user_name)
                                            localStorage.setItem("user_pwd", user_pwd)
                                            localStorage.setItem("user_json", JSON.stringify(obj.data));
                                            window.location.href = "./mainPage.jsp";
                                            layer.closeAll()
                                        }
                                    )
                                });

                            }
                        },
                        error: function () {
                            console.log("network error!");
                        }
                    });



            }


        })


    })
</script>
</body>
</html>
