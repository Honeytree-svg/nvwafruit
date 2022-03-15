<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="valuebean.Category" %>
<%@ page import="dao.factory.FactoryDao" %>
<%@ page contentType="text/html;charset=gb2312"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" />
    <link rel="stylesheet" href="css/Site.css" />
    <link rel="stylesheet" href="css/zy.all.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/admin.css" />
</head>

<body>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">

            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4"
                 style="padding-top: 30px;">
                <form class="am-form am-form-horizontal"action="../Merchandise?type=add&&identification=2" method="post" enctype="multipart/form-data">
                    <div class="am-form-group">
                        <label for="name" class="am-u-sm-3 am-form-label">
                            水果名称</label>
                        <div class="am-u-sm-9">
                            <input type="text" id="name" required
                                   placeholder="商品名称" name="merchandiseName">
                            <small>10字以内...</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <%--@declare id="user-email"--%><label for="user-email" class="am-u-sm-3 am-form-label">
                        水果分类</label>
                        <div class="am-u-sm-9">
                            <select name="merchandiseCategory" required>
                                <% List<Category>categoryList= FactoryDao.getCategoryDaoInstance().selectAll();
                                    int i=0;
                                    Category category=null;
                                    while (i<categoryList.size()){
                                        category=(Category) categoryList.get(i);
                                %>
                                <option value="<%=category.getIdcategory()%>"><%=category.getName()%></option>
                                <%i++;}%>
                            </select>
                        </div>

                    </div>

                    <div class="am-form-group">
                        <label for="name" class="am-u-sm-3 am-form-label">水果价格</label>
                        <div class="am-u-sm-9">
                            <input type="text" id="price" required placeholder="" name="merchandisePrice"  onkeyup="clearNoNum(this)" value="" οninput="if(value>100)value=100;if(value<0)value=0"
                                   oninput="if(value>10000)value=10000;if(value<0)value=0"  限制范围大小>
                        </div>
                        <script language="JavaScript" type="text/javascript">
                            function clearNoNum(obj){
                                obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
                                obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
                                obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
                                obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
                            }
                        </script>
                    </div>


                    <div class="am-form-group">
                        <label for="name" class="am-u-sm-3 am-form-label">折扣</label>
                        <div class="am-u-sm-9">
                            <input type="text"  required placeholder="" name="merchandiseDiscount" onkeyup="clearNoNum(this)" value="" οninput="if(value>100)value=100;if(value<0)value=0"
                                   oninput="if(value>10)value=10;if(value<1)value=1"  限制范围大小>
                        </div>
                        <script language="JavaScript" type="text/javascript">
                            function clearNoNum(obj){
                                obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
                                obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
                                obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
                                obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
                            }
                        </script>
                    </div>

                    <div class="am-form-group">
                        <label for="name" class="am-u-sm-3 am-form-label">水果图片</label>
                        <div class="am-u-sm-9">
                            <input type="file" name="photo" size="30">
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <input type="submit" class="am-btn am-btn-success" value="添加" />
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
