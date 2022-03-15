<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/3
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Person" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" />
    <link rel="stylesheet" href="Backstage/css/Site.css" />
    <link rel="stylesheet" href="Backstage/css/zy.all.css" />
    <link rel="stylesheet" href="Backstage/css/font-awesome.min.css" />
    <link rel="stylesheet" href="Backstage/css/amazeui.min.css" />
    <link rel="stylesheet" href="Backstage/css/admin.css" />
</head>
<body>
<%
    if(request.getAttribute("err")!=null){
%>
<h2><%=request.getAttribute("err")%></h2>
<%}%>
<div class="dvcontent">

    <div>
        <!--tab start-->
        <div class="tabs">
            <div class="hd">
                <ul>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=1&&category=0">删除用户</a></li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=2&&category=0">删除商人</a></li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=0&&category=0">删除管理员</a></li>
                    <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Backstage/addAdmin.jsp">添加管理员</a></li>
                </ul>
            </div>
            <div class="bd">
                <ul style="display: block;padding: 20px;">
                    <li>

                        <!--分页显示角色信息 start-->
                        <div id="dv1">
                            <form class="am-form am-form-horizontal" action="Person?type=add&&identification=0" method="post">

                                <div class="am-form-group">
                                    <label for="user-name" class="am-u-sm-3 am-form-label">
                                        用户名 / username </label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="user-name"  placeholder="用户名 / username" name="username">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-name" class="am-u-sm-3 am-form-label">
                                        密码 / password </label>
                                    <div class="am-u-sm-9">
                                        <input type="text" id="registPassword"  placeholder="密码 / password" name="password">
                                    </div>
                                </div>
                                <input type="hidden" name="identification" value="0">

                                <div class="am-form-group">
                                    <label for="user-name" class="am-u-sm-3 am-form-label">性别 / sex </label>
                                    <div class="am-u-sm-9" style="line-height: 30px;">
                                        <input type="radio" id="man" name="sex" value="1"/>
                                        <label for="man">男 </label> &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="radio" id="woman" name="sex" value="0" />
                                        <label for="woman"> 女 </label> <br />
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <input type="submit" class="am-btn am-btn-success" value="添加管理员" />
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!--分页显示角色信息 end-->
                    </li>
                </ul>
            </div>
            <!--tab end-->
        </div>


        <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="js/plugs/Jqueryplugs.js" type="text/javascript"></script>
        <script src="js/_layout.js"></script>
        <script src="js/plugs/jquery.SuperSlide.source.js"></script>
        <script>
            var num = 1;
            $(function() {

                $(".tabs").slide({ trigger: "click" });

            });


            var btn_save = function() {
                var pid = $("#RawMaterialsTypePageId  option:selected").val();
                var name = $("#RawMaterialsTypeName").val();
                var desc = $("#RawMaterialsTypeDescription").val();
                var ramark = $("#Ramark").val();

            }


        </script>

    </div>
</div>
</body>

</html>


