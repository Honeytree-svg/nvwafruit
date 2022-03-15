<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Merchandise" %>
<%@ page import="valuebean.Category" %>
<%@ page import="valuebean.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <form action="Merchandise?type=selectLike" method="post" class="subBox" name="namelike">
            <div class="subBoxDiv">
                <input type="text" class="subLeft" />
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
            <h2 class="Title">所有商品分类
                <ul class="InPorNav" style="display: none">
                    <%List<Category> categoryList= FactoryDao.getCategoryDaoInstance().selectAll();
                        int i=0;
                        while (i<6&&i<categoryList.size()){
                            Category category=categoryList.get(i);
                    %>
                    <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                    </li>
                    <%
                            i++;}
                    %>
                    <li><a href="Merchandise">更多>>></a>
                    </li>
                </ul><!--InPorNav/-->
            </h2>
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
        当前位置：<a href="index.jsp">首页</a> &gt; <a href="#" class="posCur">促销商品</a>
    </div><!--positions/-->
    <div class="cont">
        <div class="jilu">
            总计 <span>8</span> 个记录
        </div><!--jilu/-->
        <div class="contLeft">
            <ul class="leftPorNav">
                <%i=0;
                    while (i<10&&i<categoryList.size()){
                        Category category=(Category) categoryList.get(i);
                %>
                <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                </li>
                <%i++;}%>
            </ul>
        </div><!--contLeft/-->
        <%
            List<Merchandise> merchandiseList=(List)request.getAttribute("MerchandiseList");
        %>
        <div class="contRight">
            <div class="frProList">
                <% i=0;
                    while (i<merchandiseList.size()){
                        Merchandise merchandise=(Merchandise) merchandiseList.get(i);
                        if (merchandise.getAudit()==0){
                            i++;
                            continue;
                        }
                %>
                <dl>
                    <dt><a href="Merchandise?type=idmerchandise&&idmerchandise=<%=merchandise.getIdmerchandise()%>"><img src="upload/<%=merchandise.getPhoto()%>" width="132" height="129" /></a></dt>
                    <dd><%=merchandise.getName()%></dd>
                    <dd class="cheng">￥<%=merchandise.getDiscountPrice()%>/斤 <span>￥<%=merchandise.getPrice()%>/斤</span></dd>
                </dl>
                <%
                        i++;}
                %>
                <div class="clears"></div>
                <%
                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                %>
                <table width="800px">
                    <tr>
                        <td  align="center" style="font-size: 15px;">

                            <a href="Merchandise?type=productAll&&page=1">首页</a>
                            <%
                                i=1;
                                while (i<=pageCount){
                            %>
                            <a href="Merchandise?type=productAll&&page=<%=i%>"><%="第"+i+"页"%></a>
                            <%
                                    i++;
                                }%>
                            <a href="Merchandise?type=productAll&&page=<%=pageCount%>">尾页</a>
                        </td>
                    </tr>
                </table><!--  分页栏！！！！！ -->
                <div class="clears"></div>
            </div><!--frProList-->
        </div><!--contRight/-->
        <div class="clears"></div>
    </div><!--cont/-->
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
