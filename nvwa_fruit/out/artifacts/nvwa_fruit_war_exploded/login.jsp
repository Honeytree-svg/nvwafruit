<%--
  Created by IntelliJ IDEA.
  User: Thornchg
  Date: 2018/1/9
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>女娲水果商城</title>
    <link type="text/css" href="css/css.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/js.js"></script>
    <style>
        .input-wrap{
            margin: 8px 0;
        }
    </style>
</head>

<body style=" background:#FFF;">
<%
    if(request.getAttribute("err")!=null){
%>
<h2><%=request.getAttribute("err")%></h2>
<%}%>
<div class="loginLogo">
    <div class="logoMid">
        <h1 class="logo" style="height:71px;padding-top:10px;"><a href="index.jsp"><img src="images/loginLogo.jpg" width="241" height="71" /></a></h1>
        <div class="loginReg">
            还没有会员账号?&nbsp;<a href="reg.jsp">免费注册</a>
        </div><!--loginReg/-->
    </div><!--logoMid/-->
</div><!--loginLogo/-->
<div class="loginBox">
    <div class="loginLeft">
        <img src="images/logoinimg.jpg" width="716" height="376" />
    </div><!--loginLeft/-->
    <form action="Login" method="post" class="loginRight">
        <h2>会员登录</h2>
        <h3>用户名</h3>
        <input type="text" class="name" name="loginName"/>
        <h3>密码</h3>
        <input type="password" class="pwd" name="loginPassword"/>
        <div class="input-wrap">
            <INPUT TYPE="radio" NAME="role" value="admin">管理员
            <INPUT TYPE="radio" NAME="role" value="user">普通用户
            <INPUT TYPE="radio" NAME="role" value="seller">卖家
        </div>
        <div class="input-wrap">
            <label for="validationCode">验证码：</label>
            <input type="text" name="validationCode" id="validationCode" size="6">
            <img src="ValidationCode" id="img_validation_code" style="cursor: pointer" align="center">
        </div>
        <script >
            var validation_img=document.getElementById("img_validation_code");
            validation_img.onclick=function () {
                validation_img.setAttribute("src","ValidationCode?"+Math.random());
            }
        </script>
        <div class="zhuangtai">
            <input type="checkbox" checked="checked" /> 下次自动登录
        </div><!--zhuangtai/-->
        <div class="subs">
            <input type="image" src="images/sub.jpg" class="submit" />
        </div>
    </form><!--loginRight/-->
    <div class="clears"></div>
</div><!--loginBox/-->
</body>
</html>
