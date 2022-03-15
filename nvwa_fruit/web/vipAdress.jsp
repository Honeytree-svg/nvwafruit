<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="valuebean.Person" %>
<%@ page import="valuebean.Address" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.factory.FactoryDao" %>
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
            $(".vipNav dd:eq(1)").show();
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
            <FORM action="Address?type=add&&person=<%=person%>" method="post">
            <h2 class="oredrName">
                收货地址 <span class="green add">[添加地址]</span>
            </h2>
            <div class="address">
                <div class="addList">
                    <label><span class="red">* </span>详细地址:</label>
                    <input type="text" name="address"/>
                </div><!--addList-->
                <div class="addList">
                    <label><span class="red">* </span>邮政编码:</label>
                    <input type="text" name="postcode"/>
                </div><!--addList-->
                <div class="addList">
                    <label><span class="red">* </span>收件人:</label>
                    <input type="text" name="receiver"/>
                </div><!--addList-->
                <div class="addList">
                    <label><span class="red">* </span>手机号码:</label>
                    <input type="text" name="phone"/>
                </div><!--addList-->
                <div class="addList2">
                    <input type="image" src="images/queren.jpg" width="100" height="32" />
                </div><!--addList2/-->
            </div><!--address/-->
            </FORM>
            <table class="vipAdress">
                <tr>
                    <th>收货人</th>
                    <th>地址</th>
                    <th>邮编</th>
                    <th>电话/手机</th>
                    <th>操作</th>
                </tr>
                <% List<Address> addressList=(List)request.getAttribute("AddressList");
                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                    if(addressList==null ||addressList.size()==0){
                %><tr><td colspan="5">暂时没有任何收货地址！</td></tr>
                <tr>
                    <%}
                    else{
                        i=0;
                        Address address=null;
                        while (i<addressList.size()){
                            System.out.println(i);
                            System.out.println(addressList.size());
                            address=(Address)addressList.get(i);
                    %>
                    <td><%=address.getReceiver() %></td>
                    <td><%=address.getIdaddress() %></td>
                    <td><%=address.getPostcode() %></td>
                    <td><%=address.getPhone() %></td>
                    <td class="delete">
                        <a class="btn btn-default btn-xs" href="Address?type=update&&idaddress=<%=address.getIdaddress()%>&&iduser=<%=address.getIduser()%>">修改</a>
                        |<a class="btn btn-default btn-xs" href="Address?type=delete&&idaddress=<%=address.getIdaddress()%>&&iduser=<%=address.getIduser()%>">删除</a></td>
                </tr>
                <%
                            i++;
                        }}%>
                <tr>
                    <td colspan="6" align="center">
                        <ul class="pagination">
                            <li><a href="Address?page=1&&iduser=<%=person.getId()%>"><span>&laquo;</span></a></li>
                            <%
                                i=1;
                                while (i<=pageCount){
                            %>
                            <li><a href="Address?page=<%=i%>&&iduser=<%=person.getId()%>"><%=i%></a></li>
                            <%
                                    i++;
                                }%>
                            <li><a href="Address?page=<%=pageCount%>&&iduser=<%=person.getId()%>"><span>&raquo;</span></a></li>
                        </ul>
                    </td>
                </tr>
            </table><!--vipAdress/-->
        </div>
        <!--contRight/-->
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
