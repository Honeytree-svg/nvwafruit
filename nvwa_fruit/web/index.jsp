<%--
  Created by IntelliJ IDEA.
  User: Thornchg
  Date: 2018/1/9
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="valuebean.Person" %>
<%@ page import="valuebean.Address" %>
<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Category" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>女娲水果商城</title>
  <link type="text/css" href="css/css.css" rel="stylesheet" />
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/js.js"></script>
  <script src="js/wb.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
    $(function(){
      $("#kinMaxShow").kinMaxShow();
    });
  </script>
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
          <a href="Merchandise?type=selectName&&name=金桔">金橘</a>&nbsp;
          <a href="Merchandise?type=selectName&&name=新疆葡萄">葡萄</a> &nbsp;
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
      <ul class="InPorNav">
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
        <li class="navCur"><a href="index.jsp">商城首页</a></li>
        <li><a href="Merchandise">促销中心</a></li>
        <li><a href="vip.jsp">会员中心</a></li>
        <li><a href="#">帮助中心</a></li>
        <div class="clears"></div>
        <div class="phone">TEL：021-12345678</div>
      </ul><!--nav/-->
      <div class="banner">
        <div id="kinMaxShow">
          <div>
            <a href="#"><img src="images/ban1.jpg" height="360"  /></a>
          </div>
          <div>
            <a href="#"><img src="images/ban2.jpg" height="360"  /></a>
          </div>
          <div>
            <a href="#"><img src="images/ban3.jpg" height="360"  /></a>
          </div>
          <div>
            <a href="#"><img src="images/ban4.jpg" height="360"  /></a>
          </div>
          <div>
            <a href="#"><img src="images/ban5.jpg" height="360"  /></a>
          </div>
        </div><!--kinMaxShow/-->
      </div><!--banner/-->
    </div><!--pntRight/-->
    <div class="clears"></div>
  </div><!--pnt/-->


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

