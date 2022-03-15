<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
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
                    <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;">评论管理</li>
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
                                    <th>评论编号</th>
                                    <th>订单编号</th>
                                    <th>用户ID</th>
                                    <th>商品编号</th>
                                    <th>满意度</th>
                                    <th>评论日期</th>
                                    <th>删除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% List list=(List)request.getAttribute("CommentList");
                                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                    if(list==null ||list.size()==0){
                                %>
                                <tr><td colspan="6">暂时没有任何分类信息！</td></tr>
                                <tr>
                                        <%}else{
                                              Comment comment=null;
                                              for (int i=0;i<list.size();i++){
                                              comment=(Comment) list.get(i);
                                          %>

                                <tr>
                                    <td><%=comment.getIdcomment()%></td>
                                    <td><%=comment.getIdorder()%></td>
                                    <td><%=comment.getIduser()%></td>
                                    <td><%=comment.getIdmerchandise()%></td>
                                    <td><%=comment.getEvaluate()%></td>
                                    <td><%=comment.getDate()%></td>
                                    <td><a href="Comment?type=delete&&idcomment=<%=comment.getIdcomment()%>">删除</a></td>
                                </tr>
                                <% }}%>
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
