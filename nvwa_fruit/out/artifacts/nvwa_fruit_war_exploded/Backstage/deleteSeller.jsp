<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/3
  Time: 16:28
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
<div class="dvcontent">

    <div>
        <!--tab start-->
        <div class="tabs">
            <div class="hd">
                <ul>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=1&&category=0">删除用户</a></li>
                    <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=2&&category=0">删除商人</a></li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Person?identification=0&&category=0">删除管理员</a></li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a  href="Backstage/addAdmin.jsp">添加管理员</a></li>
                </ul>
            </div>
            <div class="bd">
                <ul style="display: block;padding: 20px;">
                    <li>

                        <!--分页显示角色信息 start-->
                        <div id="dv1">
                            <table class="table" id="tbRecord">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>电话</th>
                                    <th>删除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% List list=(List)request.getAttribute("PersonList");
                                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                    if(list==null ||list.size()==0){
                                %><tr><td colspan="5">暂时没有任何用户注册！</td></tr>
                                <tr>
                                    <%}
                                    else{
                                        int i=0;
                                        Person person=null;
                                        while (i<list.size()){
                                            person=(Person)list.get(i);
                                    %>
                                    <td><%=person.getId() %></td>
                                    <td><%=person.getName() %></td>
                                    <td><%=person.getSex() %></td>
                                    <td><%=person.getPhone() %></td>
                                    <td class="delete"><a class="btn btn-default btn-xs" href="Person?type=delete&&iduser=<%=person.getId()%>&&identification=2">删除</a></td>
                                </tr>
                                <%
                                            i++;
                                        }}%>
                                <tr>
                                    <td colspan="5" align="center">
                                        <a href="Person?page=1&&identification=2"><span>&laquo;</span></a>
                                        <%
                                            int i=1;
                                            while (i<=pageCount){
                                        %>
                                        <a href="Person?page=<%=i%>&&identification=2"><%=i%></a>
                                        <%i++;
                                        }
                                        %>
                                        <a href="Person?page=<%=pageCount%>&&identification=2"><span>&raquo;</span></a>
                                    </td>
                                </tr>
                                </tbody>

                            </table>
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


