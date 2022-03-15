<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/5
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Order" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Or" %>
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
                    <li>带处理订单</li>
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
                                    <th>订单号</th>
                                    <th>用户ID</th>
                                    <th>金额</th>
                                    <th>创建日期</th>
                                    <th>发货日期</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% List<Order> list=(List)request.getAttribute("orderList");
                                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                    if(list==null ||list.size()==0){
                                %><tr><td colspan="6" align="center">暂时没有任何退货或待处理订单！</td></tr>
                                <tr>
                                    <%}
                                    else{
                                        int i=0;
                                        Order order=null;
                                        while (i<list.size()){
                                            if(list.get(i).getState()==2){
                                            order=(Order)list.get(i);
                                    %>
                                    <td><%=order.getIdStringOrder() %></td>
                                    <td><%=order.getIduser() %></td>
                                    <td><%=order.getSumDiscount() %></td>
                                    <td><%=order.getCreateTime() %></td>
                                    <td><%=order.getDispatchTime() %></td>
                                    <td class="delete">
                                        <a class="btn btn-default btn-xs" href="Backstage/orderDetaile?order=<%=order%>">详情</a>
                                        <a class="btn btn-default btn-xs" href="Order?type=delete&&idorder=<%=order.getIdorder()%>&&identification=1">删除</a>
                                    </td>
                                </tr>
                                <%
                                            }
                                            i++;
                                            }}%>
                                <tr>
                                    <td colspan="6" align="center">
                                        <a href="Order?page=1&&identification=0"><span>&laquo;</span></a>
                                        <%
                                            int i=1;
                                            while (i<=pageCount){
                                        %>
                                        <a href="Order?page=<%=i%>&&identification=0"><%=i%>||</a>
                                        <%i++;
                                            }
                                        %>
                                        <a href="Order?page=<%=pageCount%>&&identification=0"><span>&raquo;</span></a>

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
        <!--tab end-->

    </div>


    <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="js/plugs/Jqueryplugs.js" type="text/javascript"></script>
    <script src="js/_layout.js"></script>
    <script src="js/plugs/jquery.SuperSlide.source.js"></script>

</div>
</body>

</html>
