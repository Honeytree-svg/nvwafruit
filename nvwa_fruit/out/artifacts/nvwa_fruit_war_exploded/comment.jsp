<%@ page import="dao.factory.FactoryDao" %>
<%@ page import="java.util.List" %>
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
    <script type="text/javascript">
        $(function(){
            $(".vipNav dd:eq(2)").show();
        })
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

        <form action="Merchandise?type=selectLike" method="post" class="subBox">
            <div class="subBoxDiv">
                <input type="text" class="subLeft"  name="namelike"/>
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
                    <%List<Category> categoryList= FactoryDao.getCategoryDaoInstance().selectAll();;
                        int i=0;
                        while (i<6&&i<categoryList.size()){
                            Category category=categoryList.get(i);
                    %>
                    <li><a href="Merchandise?type=selectCategory&&idcategory=<%=category.getIdcategory()%>"><%=category.getName()%></a>
                    </li>
                    <%
                            i++;}
                    %>
                    <li><a href="Merchandise?type=productAll">更多>>></a>
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
        当前位置：<a href="index.jsp">首页</a> &gt; <a href="vip.jsp">会员中心</a>
        &gt; <a href="#" class="posCur">我的订单</a>
    </div><!--positions/-->
    <div class="cont">
        <div class="contLeft" id="contLeft">
            <h3 class="leftTitle">会员中心</h3>
            <dl class="helpNav vipNav">
                <dt>我的主页</dt>
                <dd>
                    <a href="Order">我的订单</a>
                    <a href="Collection">我的收藏</a>
                </dd>
                <dt>账户设置</dt>
                <dd>
                    <a href="vip.jsp">个人信息</a>
                    <a href="vipPwd.jsp">密码修改</a>
                    <a href="Address?iduser=<%=person.getId()%>">收货地址</a>
                </dd>
                <dt>客户服务</dt>
                <dd>
                    <a href="vipExit.jsp">网站使用条款</a>
                    <a href="vipTuihuo.jsp">网站免责声明</a>
                    <a href="message.jsp">在线留言</a>
                </dd>
            </dl><!--helpNav/-->
        </div><!--contLeft/-->
        <div class="contRight">
            <h2 class="oredrName">
                发表评价
            </h2>
            <%
                int idmerchandise=Integer.parseInt(request.getParameter("idmerchandise"));
                int idorder=Integer.parseInt(request.getParameter("idorder"));
            %>
            <form action="Comment?type=add&&idorder=<%=idorder%>&&iduser=<%=person.getId()%>&&idMerchandise=<%=idmerchandise%>" method="post" class="Evaluation">
                <div class="addList">&nbsp;&nbsp;
                    <strong>描述相符：</strong><select name="bmon">
                        <option value="非常差">非常差</option>
                        <option value="差">差</option>
                        <option value="一般">一般</option>
                        <option value="好">好</option>
                        <option value="非常好">非常好</option></select>
                </div><!--addList-->
                <div class="addList" style="height:105px;">
                    <label><strong>评价内容:</strong></label>
                    <textarea style="width:220px;height:102px;" name="comment">
                    </textarea>
                </div><!--addList-->
                <div class="addList2">
                    <input type="submit" value="发布" />
                </div><!--addList2/-->
            </form><!--message/-->
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
