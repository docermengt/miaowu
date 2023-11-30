
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>

</head>
<body>
<style>

  .logo a{
    position: absolute;
    top: 50px;
    left: 100px;
  }

  * {
    margin: 0;
    padding: 0;
  }

  html {
    height: 100%;
    width: 100%;
    overflow: hidden;
    margin: 0;
    padding: 0;
    background-color: #ffffff;
    background-size:  100%;
    -moz-background-size: 100%;
  }

  body {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
  }

  #loginDiv {
    width: 37%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 300px;
    background-color: #f4aaaa;
    box-shadow: 7px 7px 17px rgba(52, 56, 66, 0.5);
    border-radius: 5px;
  }


  p {
    margin-top: 30px;
    margin-left: 20px;
    color: azure;
  }

  input {
    margin-left: 15px;
    border-radius: 5px;
    border-style: hidden;
    height: 30px;
    width: 140px;
    background-color: white;
    outline: none;
    padding-left: 10px;
  }
  #username{
    width: 200px;
  }
  #password{
    width: 202px;
  }
  .button {
    border-color: cornsilk;
    background-color: rgb(223 81 81 / 70%);
    color: aliceblue;
    border-style: hidden;
    border-radius: 5px;
    width: 100px;
    height: 31px;
    font-size: 16px;
  }

  #subDiv {
    text-align: center;
    margin-top: 30px;
  }
  #loginMsg{
    text-align: center;color: aliceblue;
  }
  #subDiv a{
    color: white;
  }
  .site-logo{
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


<header >
  <div class="logo">
     <a href="#" class="site-logo"></a>
  </div>
  <img src="../imgs/index_Client.png">
</header>
<div>hhh</div>
<div id="loginDiv">
  <form action="#" method="post" id="form">
    <h1 id="loginMsg">LOGIN IN</h1>
    <p>用户名:<input id="username" name="username" type="text"></p>

    <p>密      码:  <input id="password" name="password" type="password"></p>

    <div id="subDiv">
      <input type="submit" class="button" value="login up">
      <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
      <a href="register.jsp">没有账号？点击注册</a>
    </div>
  </form>
</div>

</body>
</html>
