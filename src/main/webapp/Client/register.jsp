
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<style>
    * {
        margin: 0;
        padding: 0;
        list-style-type: none;
    }
    .reg-content{
        padding: 25px;
        margin: 2px;
    }
    a, img {
        border: 0;
        color: white;
    }



    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    td, th {
        color: white;
    }
    .inputs{
        vertical-align: top;
    }

    .clear {
        clear: both;
    }

    .clear:before, .clear:after {
        content: "";
        display: table;
    }

    .clear:after {
        clear: both;
    }

    .form-div {
        background-color:#f4aaaa;
        border-radius: 10px;
        border: 1px solid rgb(223 81 81 / 70%);
        width: 37%;
        margin-top: 260px;
        margin-left:700px;
        padding: 30px 0 20px 0px;
        font-size: 16px;
        box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(75, 75, 75, 0.3);
        text-align: left;
        color: white;
    }

    .form-div input[type="text"], .form-div input[type="password"], .form-div input[type="email"] {
        width: 230px;
        margin: 10px;
        line-height: 20px;
        font-size: 16px;
    }

    .form-div input[type="checkbox"] {
        margin: 20px 0 20px 10px;
    }

    .form-div input[type="button"], .form-div input[type="submit"] {
        margin: 10px 20px 0 0;
    }

    .form-div table {
        margin: 0 auto;
        text-align: right;
        color: rgba(64, 64, 64, 1.00);
    }

    .form-div table img {
        vertical-align: middle;
        margin: 0 0 5px 0;
    }

    .form-div .buttons {
        float: right;
    }

    input[type="text"], input[type="password"], input[type="email"] {
        border-radius: 8px;
        box-shadow: inset 0 2px 5px #eee;
        padding: 10px;
        border: 1px solid #D4D4D4;
        color: #333333;
        margin-top: 5px;
    }

    input[type="text"]:focus, input[type="password"]:focus, input[type="email"]:focus {
        border: 1px solid #50afeb;
        outline: none;
    }

    input[type="button"], input[type="submit"] {
        padding: 7px 15px;
        background-color: #3c6db0;
        text-align: center;
        border-radius: 5px;
        overflow: hidden;
        min-width: 80px;
        border: none;
        color: #FFF;
        box-shadow: 1px 1px 1px rgba(75, 75, 75, 0.3);
    }

    input[type="button"]:hover, input[type="submit"]:hover {
        background-color: #5a88c8;
    }

    input[type="button"]:active, input[type="submit"]:active {
        background-color: #5a88c8;
    }
    .err_msg{
        color: red;
        padding-right: 170px;
    }
    #password_err,#tel_err{
        padding-right: 195px;
    }

    #reg_btn{
        margin-right:180px; width: 260px; height: 50px; margin-top:20px;
        background-color: rgb(223 81 81 / 70%);
    }

    .logo a{
        position: absolute;
        top: 50px;
        left: 100px;
    }
    .site-logo {
        width: 230px;
        height: 45px;
        background-position: -716px -677px;
        background-image: url(../imgs/logo.png);
        background-size: initial;
        margin: 0;
        background-repeat: no-repeat;
        float: left;
        display: inline;
        text-indent: -9999px;
        overflow: hidden;
    }
</style>

<div class="form-div">
    <header >
        <div class="logo">
            <a href="#" class="site-logo"></a>
        </div>
        <img src="../imgs/index_Client.png" style="position: absolute ;left: 150px">
    </header>
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="#" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: none">用户名不太受欢迎</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
</body>
</html>
