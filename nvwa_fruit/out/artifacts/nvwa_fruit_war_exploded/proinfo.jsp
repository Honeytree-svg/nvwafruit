<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Category" %>
<%@ page import="valuebean.Merchandise" %>
<%@ page import="valuebean.Comment" %>
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
    <script src="js/MagicZoom.js" type="text/javascript"></script>
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
            <h2 class="Title">所有商品分类
                <ul class="InPorNav" style="display: none">
                    <%
                        List<Category> categoryList=FactoryDao.getCategoryDaoInstance().selectAll();
                    %>
                    <%int i=0;
                        while (i<10&&i<categoryList.size()){
                            Category category=categoryList.get(i);
                    %>
                    <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                    </li>
                    <%
                            i++;}
                    %>
                    <li><a href="#">更多>>></a>
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
        <div class="contLeft">
            <ul class="leftPorNav">
                <%i=0;
                    while (i<6&&i<categoryList.size()){
                        Category category=(Category) categoryList.get(i);
                %>
                <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                </li>
                <%i++;}%>
            </ul>
        </div><!--contLeft/-->
        <%
            Merchandise merchandise=(Merchandise)request.getAttribute("Merchandise");
        %>
        <div class="contRight" style="border:0;">
            <div class="proBox">
                <div id="tsShopContainer">
                    <div id="tsImgS"><a href="upload/<%=merchandise.getPhoto()%>" title="Images" class="MagicZoom" id="MagicZoom"><img width="300" height="300" src="upload/<%=merchandise.getPhoto()%>" /></a></div>
                    <div id="tsPicContainer">
                        <div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
                        <div id="tsImgSCon">
                            <ul>
                                <li onclick="showPic(0)" rel="MagicZoom" class="tsSelectImg"><img height="42" width="42" src="upload/<%=merchandise.getPhoto()%>" tsImgS="upload/<%=merchandise.getPhoto()%>" /></li>
                                <li onclick="showPic(1)" rel="MagicZoom"><img height="42" width="42" src="upload/<%=merchandise.getPhoto()%>" tsImgS="upload/<%=merchandise.getPhoto()%>" /></li>
                                <li onclick="showPic(2)" rel="MagicZoom"><img height="42" width="42" src="upload/<%=merchandise.getPhoto()%>" tsImgS="upload/<%=merchandise.getPhoto()%>" /></li>
                                <li onclick="showPic(3)" rel="MagicZoom"><img height="42" width="42" src="upload/<%=merchandise.getPhoto()%>" tsImgS="upload/<%=merchandise.getPhoto()%>" /></li>
                                <li onclick="showPic(4)" rel="MagicZoom"><img height="42" width="42" src="upload/<%=merchandise.getPhoto()%>" tsImgS="upload/<%=merchandise.getPhoto()%>" /></li>
                            </ul>
                        </div>
                        <div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
                    </div>
                    <img class="MagicZoomLoading" width="16" height="16" src="images/loading.gif" alt="Loading..." />
                    <script src="js/ShopShow.js"></script>
                </div><!--tsShopContainer/-->
                <div class="proBoxRight">
                    <h3 class="proTitle"><%=merchandise.getName()%></h3>
                    <div>商品编号：<%=merchandise.getIdmerchandise()%> </div>
                    <div>会员价：<strong class="red">¥<%=merchandise.getDiscountPrice()%></strong> </div>
                    <div>单位：斤 </div>
                    <div>库存：<strong class="red">[有货] </strong> 从厦门发货</div>
                    <ul class="rongliang">
                        <li>100ml</li>
                        <li>200ml</li>
                        <li>300ml</li>
                        <li>400ml</li>
                        <li>500ml</li>
                        <div class="clears"></div>
                    </ul><!--rongliang/-->
                    <FORM action="ShoppingCar?type=add&&idmerchandise=<%=merchandise.getIdmerchandise()%>" method="post">

                        <div class="shuliang2">
                        数量： <input type="text" name="quantity"  onkeyup="clearNoNum(this)" value="" οninput="if(value>100)value=100;if(value<0)value=0"
                                   oninput="if(value>10000)value=10000;if(value<1)value=1"  限制范围大小/>
                        </div><!--shuliang2/-->
                        <script language="JavaScript" type="text/javascript">
                            function clearNoNum(obj){
                                obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
                                obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
                                obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
                                obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
                            }
                        </script>
                        <div class="gc">
                            <input type="submit" value="加入购物车" style="width: 117px;height: 36px;"/>
                        </div><!--gc/-->
                    </FORM>
                    <form action="Collection?type=add&&idmerchandise=<%=merchandise.getIdmerchandise()%>" method="post">
                        <div class="gc">
                            <input type="submit" value="加入收藏" style="width: 117px;height: 36px;"/>
                        </div>
                    </form>
                </div><!--proRight/-->
                <div class="clears"></div>
            </div><!--proBox/-->
            <ul class="fangyuan">
                <li><a href="Comment?type=selectIdMerchandiseCommnet&&idmerchandise=<%=merchandise.getIdmerchandise()%>">商品描述</a></li>
                <div class="clears"></div>
            </ul><!--fangyuan/-->
            <div class="fangList">
                <%
                    if (request.getAttribute("CommentList")!=null){
                        List<Person> personList=(List<Person>)request.getAttribute("PersonList");
                        List commentList=(List)request.getAttribute("CommentList");
                        for (int z=0; z<commentList.size(); z++){
                            Comment comment=(Comment) commentList.get(z);
                            Person person2=personList.get(z);
                %>
                用户名：<%=person2.getName()%><br/>
                评价：<%=comment.getComment()%><br/>
                满意度：<%=comment.getEvaluate()%><br/>
                评论日期：<%=comment.getDate()%><br/>
                <---------------------------------------------><br/>
                <%}}%>
            </div><!--fangList/-->
        </div><!--contRight/-->
        <div class="clears"></div>
    </div><!--cont/-->

    <div class="clears"></div>
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
