<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Merchandise" %>
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
                    <li class="on" style="box-sizing: initial;-webkit-box-sizing: initial;">审查商品</li>
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
                                    <th>确认上架</th>
                                    <th>驳回</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% List list=(List)request.getAttribute("MerchandiseList");
                                    int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
                                    if(list==null ||list.size()==0){
                                %><tr><td colspan="7">暂时没有审查商品！</td></tr>
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
                                        <a class="btn btn-default btn-xs" href="Merchandise?type=updateAuditState&&AuditState=1&&idmerchandise=<%=merchandise.getIdmerchandise()%>">上架</a>
                                    </td>
                                    <td class="delete">
                                        <a class="btn btn-default btn-xs" href="Merchandise?type=delete&&idmerchandise=<%=merchandise.getIdmerchandise()%>">驳回</a>
                                    </td>
                                </tr>
                                <%
                                            i++;
                                        }}%>
                                <tr>
                                    <td colspan="7" align="center">
                                        <ul class="pagination">
                                            <li><a href="Merchandise?page=1&&auditState=0&&type=audit"><span>&laquo;</span></a></li>
                                            <%
                                                int i=1;
                                                while (i<=pageCount){
                                            %>
                                            <li><a href="Merchandise?page=<%=i%>&&auditState=0&&type=audit"><%=i%></a></li>
                                            <%
                                                    i++;
                                                }%>
                                            <li><a href="Merchandise?page=<%=pageCount%>&&auditState=0&&type=audit"><span>&raquo;</span></a></li>
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
            <!--tab end-->
        </div>


        <script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <script src="js/plugs/Jqueryplugs.js" type="text/javascript"></script>
        <script src="js/_layout.js"></script>
        <script src="js/plugs/jquery.SuperSlide.source.js"></script>
        <script>
            var num = 1;
            $(function() {

                $(".tabs").slide({ trigger: "click" });

            });


            var btn_save = function() {
                var pid = $("#RawMaterialsTypePageId  option:selected").val();
                var name = $("#RawMaterialsTypeName").val();
                var desc = $("#RawMaterialsTypeDescription").val();
                var ramark = $("#Ramark").val();

            }


        </script>

    </div>
</div>
</body>

</html>

