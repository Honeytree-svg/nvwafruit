<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/4
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="valuebean.Address" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<h1 class="text-center">女娲水果，造福社会</h1>
<hr>
<%
    Address address=(Address) session.getAttribute("ADDRESS");
%>
<form action="Address?type=update_do&&iduser=<%=address.getIduser()%>" method="post">
    <input type="text" name="idaddress" value="<%=address.getIdaddress()%>" style="display: none">
    <table class="table table-bordered" style="width: 550px;margin: 0 auto">
        <tr>
        <tr>
            <td>收件人：</td>
            <td><input type="text" name="receiver" value="<%=address.getReceiver()%>"></td>
        </tr>
        <tr>
            <td>邮编：</td>
            <td><input type="text" name="postcode" value="<%=address.getPostcode()%>"></td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="phone" value="<%=address.getPhone()%>"></td>
        </tr>
        </tr>
        <tr>
        <tr>
            <td>地址：</td>
            <td><textarea cols="60" rows="5" name="address"><%=address.getAddress()%></textarea></td>
        </tr>
        </tr>
        <tr>
            <td></td>
            <td><button type="submit">提交</button></td>
        </tr>
        </tr>
    </table>
</form>
</body>
</html>
