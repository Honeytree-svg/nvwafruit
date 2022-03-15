<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Category" %>
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
    <style>

    </style>
<body>
<div class="dvcontent">

    <div>
        <!--tab start-->
        <div class="tabs">
            <div class="hd">
                <ul style="">
                    <li style="box-sizing: initial;-webkit-box-sizing: initial;" class="on">查看分类</li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a href="Backstage/addCategory.jsp">添加分类</a></li>
                </ul>
            </div>
            <div class="bd">
                <form action="" method="post">
                    <ul style="display: block;padding: 20px;">
                        <li>
                            <!--分页显示角色信息 start-->

                            <div id="dv1">
                                <table class="table" id="tbRecord">
                                    <thead>
                                    <tr>
                                        <th>分类编号</th>
                                        <th>分类名称</th>
                                        <th>备注</th>
                                        <th>编辑</th>
                                        <th>删除</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% List list=(List)request.getAttribute("CategoryList");
                                        int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                        if(list==null ||list.size()==0){
                                    %>
                                    <tr><td colspan="5">暂时没有任何分类信息！</td></tr>
                                    <tr>
                                            <%}else{
                                             int i=0;
                                              Category category=null;
                                              while (i<list.size()){
                                              category=(Category) list.get(i);
                                          %>
                                    <tr>
                                        <td><%=category.getIdcategory()%></td>
                                        <td><%=category.getName()%></td>
                                        <td><%=category.getRemark()%></td>
                                        <td><a href="Category?type=updateSingle&&idcategory=<%=category.getIdcategory()%>">编辑</a></td>
                                        <td><a href="Category?type=delete&&idcategory=<%=category.getIdcategory()%>">删除</a></td>
                                    </tr>
                                    <%i++;
                                    }}%>

                                    </tbody>
                                </table>
                            </div><!--分页显示角色信息 end-->
                        </li>
                    </ul>
                </form>
            </div>        <!--tab end-->
        </div>
    </div>
</div>
</body>
</html>

