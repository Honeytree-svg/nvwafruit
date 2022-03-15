<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Merchandise" %>
<%@ page import="valuebean.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>

<body>

<div class="dvcontent">
    <%
        Person person=(Person)session.getAttribute("user");
    %>
    <div>
        <!--tab start-->
        <div class="tabs">
            <div class="hd">
                <ul>
                    <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;">水果管理</li>
                    <li class="" style="box-sizing: initial;-webkit-box-sizing: initial;"><a href="Backstage/addMerchandise.jsp">添加水果</a></li>
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
                                    <th>水果图片</th>
                                    <th>水果名称</th>
                                    <th>水果分类</th>
                                    <th>水果价格</th>
                                    <th>水果折扣</th>
                                    <th>删除</th>

                                </tr>
                                </thead>
                                <tbody>
                                <% List list=(List)request.getAttribute("MerchandiseList");
                                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                    if(list==null ||list.size()==0){
                                %><tr><td colspan="7">暂时没有任何收货地址！</td></tr>
                                <tr>
                                    <%}
                                    else{
                                        int i=0;
                                        Merchandise merchandise=null;
                                        while (i<list.size()){
                                            merchandise=(Merchandise) list.get(i);
                                    %>
                                    <td><img src="upload/<%=merchandise.getPhoto()%>" width="100" height="50" /></td>
                                    <td><%=merchandise.getName() %></td>
                                    <td><%=merchandise.getCategory() %></td>
                                    <td><%=merchandise.getPrice() %></td>
                                    <td><%=merchandise.getDisconut()%></td>
                                    <td class="delete">
                                        <a class="btn btn-default btn-xs" href="Merchandise?type=delete&&idmerchandise=<%=merchandise.getIdmerchandise()%>">删除</a></td>
                                </tr>
                                <%
                                            i++;
                                        }}%>
                                <tr>
                                    <td colspan="7" align="center">
                                        <ul class="pagination">
                                            <li><a href="Merchandise?page=1&&idseller=<%=person.getIdentification()%>"><span>&laquo;</span></a></li>
                                            <%
                                                int i=1;
                                                while (i<=pageCount){
                                            %>
                                            <li><a href="Merchandise?page=<%=i%>&&iduser=<%=person.getIdentification()%>"><%=i%></a></li>
                                            <%
                                                    i++;
                                                }%>
                                            <li><a href="Merchandise?page=<%=pageCount%>&&iduser=<%=person.getIdentification()%>"><span>&raquo;</span></a></li>
                                        </ul>
                                    </td>
                                </tr>
                                </tbody>

                            </table>
                        </div>
                        <!--分页显示角色信息 end-->
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>