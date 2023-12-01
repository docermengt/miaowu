
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>

</head>
<body>
<style>
  @IMPORT url("../css/login.css");
</style>


<header >
  <div class="logo">
     <a href="#" class="site-logo"></a>
  </div>
  <img src="../imgs/index_Client.png">
</header>

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
