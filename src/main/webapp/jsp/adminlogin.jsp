
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>猫眼电影-管理员登录页面</title>

</head>
<body>
<style>
  @IMPORT url("../static/css/login.css");
</style>


<header >
  <div class="logo">
     <a href="#" class="site-logo"></a>
  </div>
<%--  <img src="../static/images/index_Client.png">--%>
</header>
<script src="../static/js/jquery-3.3.1.min.js"></script>
<script src="../static/js/Api.js"></script>

<div id="loginDiv">
  <form action="#" method="post" id="form">
    <h1 id="loginMsg">Admin login</h1>
    <p>用户名:<input id="username"  type="text"></p>

    <p>密      码:  <input id="password"  type="password"></p>
    <br>
    <span id="username_err"  style="display: block;color: red"></span>
    <div id="subDiv">
      <input type="button" class="button" value="login up">
      <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;

    </div>
  </form>
</div>

<script>
  $(function (){

     var admin_uname= $("#username")
    var admin_upwd= $("#password")



    $('.button').click(function (){
      var name = admin_uname.val()
      var pwd = admin_upwd.val()
      $.ajax({
        type: "post",
        url: url + "/admin/login",
        data: {
          admin_uname: name,
          admin_upwd: pwd
        },
        dataType: "json",
        success: function(obj){
          if(obj.msg == "fail"){
            $('#username_err').html("账户名或密码错误")
          }
          else{
            localStorage.setItem("admin_name",name)
            localStorage.setItem("admin_pwd",pwd)
            localStorage.setItem("admin_json",JSON.stringify(obj.data));
           window.location.href= "./manage.jsp";
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
