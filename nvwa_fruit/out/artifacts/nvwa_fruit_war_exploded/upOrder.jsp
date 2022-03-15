<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/6
  Time: 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.*" %>
<%@ page import="dao.factory.FactoryDao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>女娲水果商城</title>
    <link type="text/css" href="css/css.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/js.js"></script>
    <script src="js/wb.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
<div class="mianCont">
    <div class="top">
        <%Person person=(Person)session.getAttribute("user");%>
        <%if(session.getAttribute("user")==null){%>
        <span>您好！欢迎来到女娲水果  请&nbsp;<a href="login.jsp">[登录]</a>&nbsp;<a href="reg.jsp">[注册]</a></span>
        <%}else{%>
        <form action="Logout" method="post"><span>您好！<%=person.getName()%> &nbsp;<input type="submit" value="[注销]" size="6"/></span></form>
        <%}%>
        <span class="topRight">
    <a href="vip.jsp">我的女娲</a>&nbsp;|
    <a href="Order">我的订单</a>&nbsp;|
    <a href="login.jsp">会员登录</a>&nbsp;|
    <a href="#">联系我们</a>
   </span>
    </div><!--top/-->
    <div class="lsg">
        <h1 class="logo"><a href="index.jsp"><img src="images/logo.png" width="217" height="90" /></a></h1>

        <form action="Merchandise?type=selectLike" method="post" class="subBox" >
            <div class="subBoxDiv">
                <input type="text" class="subLeft" name="namelike"/>
                <input type="image" src="images/subimg.png" width="63" height="34" class="sub" />
                <div class="hotWord">
                    热门搜索：
                    <a href="Merchandise?type=selectName&&name=金橘">金橘</a>&nbsp;
                    <a href="Merchandise?type=selectName&&name=葡萄">葡萄</a> &nbsp;
                    <a href="Merchandise?type=selectName&&name=草莓">草莓</a>&nbsp;
                    <a href="Merchandise?type=selectName&&name=樱桃">樱桃</a>  &nbsp;
                    <a href="Merchandise?type=selectName&&name=西瓜">西瓜</a> &nbsp;
                    <a href="Merchandise?type=selectName&&name=苹果">苹果</a>&nbsp;
                </div><!--hotWord/-->
            </div><!--subBoxDiv/-->
        </form><!--subBox/-->

        <div class="gouwuche">
            <div class="gouCar">
                <img src="images/gouimg.png" width="19" height="20" style="position:relative;top:6px;" />&nbsp;|&nbsp;
                <strong class="red">0</strong>&nbsp;件&nbsp;|
                <strong class="red">￥ 0.00</strong>
                <a href="ShoppingCar">去结算</a> <img src="images/youjian.jpg" width="5" height="8" />
            </div><!--gouCar/-->
            <div class="myChunlv">
                <a href="Person?type=vip_jsp"><img src="images/mychunlv.jpg" width="112" height="34" /></a>
            </div><!--myChunlv/-->
        </div><!--gouwuche/-->
    </div><!--lsg/-->
    <div class="pnt">
        <div class="pntLeft">
            <h2 class="Title">所有商品分类</h2>
            <ul class="InPorNav" style="display: none">
                <%
                    List list= FactoryDao.getCategoryDaoInstance().selectAll();
                    int i=0;
                    while (i<list.size()){
                        Category category=(Category) list.get(i);
                %>
                <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                </li>
                <%
                        i++;}
                %>
                </li>
                <li><a href="Merchandise?type=productAll">更多>>></a>
                </li>
            </ul><!--InPorNav/-->
        </div><!--pntLeft/-->
        <div class="pntRight">
            <ul class="nav">
                <li><a href="index.jsp">商城首页</a></li>
                <li><a href="Merchandise">促销中心</a></li>
                <li><a href="vip.jsp">会员中心</a></li>
                <li><a href="#">帮助中心</a></li>
                <div class="clears"></div>
                <div class="phone">TEL：021-12345678</div>
            </ul><!--nav/-->
        </div><!--pntRight/-->
        <div class="clears"></div>
    </div><!--pnt/-->
    <div class="positions">
        当前位置：<a href="index.jsp">首页</a> &gt; <a href="#" class="posCur">填写核对订单</a>
    </div><!--positions/-->


    <form action="Order?type=success_jsp" method="post">
    <div class="cont">
        <div class="carImg"><img src="images/car2.jpg" width="961" height="27" /></div>
        <h4 class="orderTitle">收货地址</h4>
        <table class="ord">
            <%  List addressList=(List)request.getAttribute("AddressList");
                int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                if(addressList==null ||addressList.size()==0){
            %><tr><td colspan="2">暂时没有任何收货地址！</td></tr>
            <tr>
                <%}
                else{
                    i=0;
                    Address address=null;
                    while (i<addressList.size()){
                        address=(Address) addressList.get(i);
                %>
                <td width="30%">
                    <input type="radio" name="idaddress" value="<%=address.getIdaddress()%>" checked/>收货人：<%=address.getReceiver()%>
                </td>
                <td width="70%">
                    详细信息：<%=address.getAddress()%>	,<%=address.getPostcode()%>,<%=address.getPhone()%>
                </td>
            </tr>
            <%
                        i++;
                    }}%>
            <tr>
                <td><a href="ShoppingCar?page=1&&pageLable=upOrder"><span>&laquo;</span></a></td>
                <%
                    i=1;
                    while (i<=pageCount){
                %>
                <td><a href="ShoppingCar?page=<%=i%>&&pageLable=upOrder"><%=i%></a></td>
                <%
                        i++;
                    }%>
                <td><a href="ShoppingCar?page=<%=pageCount%>&&pageLable=upOrder"><span>&raquo;</span></a></td>
            </tr>
        </table><!--ord/-->
        <h4 class="orderTitle">购物清单</h4>
        <table class="orderList">
            <tr>
                <th width="20"></th>
                <th width="430">商品</th>
                <th width="135">单价</th>
                <th width="135">数量</th>
                <th width="135">总金额</th>
                <th>操作</th>
            </tr>
            <% Shopping_Car shoppingcar=(Shopping_Car)request.getSession().getAttribute("ShoppingCar");
                if(shoppingcar.getOrder_merchandises()==null ||shoppingcar.getOrder_merchandises().size()==0){
            %><tr><td colspan="6">购物车中暂时没有任何商品！</td></tr>
            <tr>
                <%}
                else{
                    i=0;
                    while (i<shoppingcar.getOrder_merchandises().size()){
                       Order_Merchandise merchandise=shoppingcar.getOrder_merchandises().get(i);
                %>
                <td><input type="checkbox" name="idmerchandise" value="<%=merchandise.getMerchandise().getIdmerchandise()+"+"+merchandise.getQuantity()%>"/></td>
                <td>
                    <dl>
                        <dt><a href="proinfo.jsp?idmerchandise=<%=merchandise.getMerchandise().getIdmerchandise()%>"><img src="upload/<%=merchandise.getMerchandise().getPhoto()%>" width="85" height="85" /></a></dt>
                        <dd><%=merchandise.getMerchandise().getName()%></dd>
                        <div class="clears"></div>
                    </dl>
                </td>
                <td><strong class="red">￥<%=merchandise.getMerchandise().getPrice()%></strong></td>
                <td>
                    <strong ><%=merchandise.getQuantity()%></strong>
                </td>
                <td><strong class="red">￥<%=merchandise.getSum()%></strong></td>
                <td><a href="ShoppingCar?delete&&pageLable=upOrder&&idmerchandise=<%=merchandise.getMerchandise().getIdmerchandise()%>" class="green">删除</a></td>
            </tr>
            <%
                        i++;
                    }}%>

            <tr>
                <td colspan="6"><div class="shanchu"><img src="images/lajio.jpg" /> 全部删除</div></td>
            </tr>
        </table><!--orderList/-->
        <table class="zongjia" align="right">
            <tr>
                <td colspan="2" style="height:50px;">
                    <input src="images/tijao.png" width="142" height="32" type="submit"/>
                </td>
            </tr>
        </table><!--zongjia/-->
        <div class="clears"></div>
    </div><!--cont/-->
    </form>


    <div class="inHelp">
        <div class="inHLeft">
            <h4>帮助中心</h4>
            <ul class="inHeList">
                <li><a href="#">购物指南</a></li>
                <li><a href="#">支付方式</a></li>
                <li><a href="#">售后服务</a></li>
                <li><a href="#">企业简介</a></li>
                <div class="clears"></div>
            </ul><!--inHeList/-->
        </div><!--inHLeft/-->
        <div class="inHLeft">
            <h4>会员服务</h4>
            <ul class="inHeList">
                <li><a href="reg.jsp">会员注册</a></li>
                <li><a href="login.jsp">会员登录</a></li>
                <li><a href="ShoppingCar">购物车</a></li>
                <li><a href="Order">我的订单</a></li>
                <div class="clears"></div>
            </ul><!--inHeList/-->
        </div><!--inHLeft/-->
        <div class="inHRight">
            <h4>全国统一免费服务热线</h4>
            <div class="telBox">400-0000-0000</div>
            <div class="weibo">
                <wb:follow-button uid="2991975565" type="red_1" width="67" height="24" ></wb:follow-button>
            </div>
        </div><!--inHRight/-->
        <div class="clears"></div>
    </div><!--inHelp/-->
</div><!--mianCont/-->
<a href="#" class="backTop">&nbsp;</a>
</body>
</html>
