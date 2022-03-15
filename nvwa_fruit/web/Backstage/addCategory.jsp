<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/7
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>

    </style>

<body>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
    </div>
    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4" style="padding-top: 30px;">
        <form class="am-form am-form-horizontal" action="../Category?type=add&&identification=9" method="post">

            <div class="am-form-group">
                <label for="user-name" class="am-u-sm-3 am-form-label">
                    分类名称</label>
                <div class="am-u-sm-9">
                    <input type="text" id="user-name" required placeholder="分类名称" name="categoryname">
                    <small>10字以内...</small>
                </div>
            </div>
            <div class="am-form-group">
                <label for="user-intro" class="am-u-sm-3 am-form-label">
                    备注</label>
                <div class="am-u-sm-9">
                    <textarea class="" rows="5" id="user-intro" name="categoryremark" placeholder="输入备注"></textarea>
                    <small>250字以内...</small>
                </div>
            </div>
            <div class="am-form-group">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <input type="submit" class="am-btn am-btn-success" value="添加分类" />
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="js/plugs/Jqueryplugs.js" type="text/javascript"></script>
<script src="js/_layout.js"></script>
<script src="js/plugs/jquery.SuperSlide.source.js"></script>
<script>
    var num = 1;
    $(function() {
        $(".tabs").slide({ trigger: "click" });
    });

</script>
</html>

