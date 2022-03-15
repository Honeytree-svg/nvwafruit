<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/2
  Time: 10:58
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
</head>

<body style=" background:#FFF;">
<%
    if(request.getAttribute("err")!=null){
%>
<h2><%=request.getAttribute("err")%></h2>
<%}%>
<div class="loginLogo">
    <div class="logoMid">
        <h1 class="logo" style="height:71px;padding-top:10px;"><a href="index.html"><img src="images/loginLogo.jpg" width="241" height="71" /></a></h1>
        <div class="loginReg">
            还没有会员账号?&nbsp;<a href="login.html">登录</a>
        </div><!--loginReg/-->
    </div><!--logoMid/-->
</div><!--loginLogo/-->
<div class="loginBox">
    <img src="images/chengnuo.jpg" width="295" height="393" class="chengnuo" />
    <form action="Register" method="post" class="reg" onsubmit="return checkPwd()">
        <div class="regList">
            <label><span class="red">*</span> 账户名</label>
            <input type="text" name="registName"/> <span style="color:#999;">请输入邮箱/用户名/手机号</span>
        </div><!--regList/-->
        <div class="regList">
            <label><span class="red">*</span> 请设置密码</label>
            <input type="password" name="registPassword" id="registPassword"/>
        </div><!--regList/-->
        <div class="regList">
            <label><span class="password">*</span> 请确认密码</label>
            <input type="text" name="registPassword2" id="registPassword2"/>
        </div><!--regList/-->

        <div class="input-wrap">
            <INPUT TYPE="radio" NAME="identification" value="1">普通用户
            <INPUT TYPE="radio" NAME="identification" value="2">卖家
        </div>

        <div class="regList">
            <label for="validationCode"><span class="red">*</span> 验证码：</label>
            <input type="text" class="yanzheng" name="validationCode" id="validationCode" size="6">
            <img src="ValidationCode" id="img_validation_code" style="cursor: pointer" align="center" width="103" height="38">
        </div><!--regList/-->
        <script >
            var validation_img=document.getElementById("img_validation_code");
            validation_img.onclick=function () {
                validation_img.setAttribute("src","ValidationCode?"+Math.random());
            }
            //检查两次输入的密码是否一致
            function checkPwd() {
                var pwd=document.getElementById("registPassword").value;
                var againPwd=document.getElementById("registPassword2").value;
                if(pwd===againPwd)
                    return true;
                else{
                    alert("-两次输入的密码不一致！");
                    return false;
                }
            }
        </script>
        <div class="xieyi">
            <input type="checkbox" name="protocal" checked/> 我已经阅读并同意<a href="#">《17用户注册协议》</a>
        </div><!--xieyi/-->
        <div class="reg">
            <input type="image" src="images/reg.jpg" width="284" height="34" />
        </div><!--reg/-->
    </form><!--reg/-->
    <div class="clears"></div>
</div><!--loginBox/-->
</body>
</html>
