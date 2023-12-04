
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>猫眼电影-登录页面</title>

</head>
<body>
<style>
  @IMPORT url("../static/css/login.css");
</style>


<header >
  <div class="logo">
     <a href="#" class="site-logo"></a>
  </div>
  <img src="../static/images/index_Client.png">
</header>
<%
  String l = (String) request.getSession().getAttribute("l");
  String erro="";
  if(l!=null){
    erro = l;
  }else {
    erro = "";
  }
%>
<div id="loginDiv">
  <form action="/movie/user/login" method="post" id="form">
    <h1 id="loginMsg">LOGIN IN</h1>
    <p>用户名:<input id="username" name="username" type="text"></p>

    <p>密      码:  <input id="password" name="password" type="password"></p>
    <br>
    <span id="username_err"  style="display: block;color: red"><%=erro%></span>
    <div id="subDiv">
      <input type="submit" class="button" value="login up">
      <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
      <a href="register.jsp">没有账号？点击注册</a>
    </div>
  </form>
</div>

</body>
</html>
