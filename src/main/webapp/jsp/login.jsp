
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
<script src="../static/js/jquery-3.3.1.min.js"></script>
<script src="../static/js/Api.js"></script>
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
  <form action="#" method="post" id="form">
    <h1 id="loginMsg">LOGIN IN</h1>
    <p>用户名:<input id="username"  type="text"></p>

    <p>密      码:  <input id="password"  type="password"></p>
    <br>
    <span id="username_err"  style="display: block;color: red"></span>
    <div id="subDiv">
      <input type="button" class="button" value="login up">
      <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
      <a href="register.jsp">没有账号？点击注册</a>
    </div>
  </form>
</div>

<script>
  $(function (){

     var username= $("#username")
    var userpwd= $("#password")



    $('.button').click(function (){
      var user_name = username.val()
      var user_pwd = userpwd.val()
      localStorage.setItem("user_name",user_name)

      $.ajax({
        type: "post",
        url: url + "/user/login",
        data: {
          user_name: user_name,
          user_pwd: user_pwd
        },
        dataType: "json",
        success: function(obj){
          if(obj.msg == "fail"){
            $('#username_err').html("账户名或密码错误")
          }
          else{

            localStorage.setItem("user_json",JSON.stringify(obj.data));
           window.location.href="./mainPage.jsp";
          }
        },
        error:function(){
          console.log("network error!");
        }
      });
    })

  })

</script>
</body>
</html>
