<%--
  Created by IntelliJ IDEA.
  User: A450j
  Date: 2020/1/3
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/Site.css" />
    <link rel="stylesheet" href="css/zy.all.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" />
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/admin.css" />
</head>
<body>
<div class="am-cf admin-main" style="padding-top: 0px;">
    <!-- content start -->

    <div class="am-cf admin-main" style="padding-top: 0px;">
        <!-- content start -->
        <div class="admin-content">
            <div class="admin-content-body">

                <div class="am-g">
                    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">

                    </div>
                    <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4" style="padding-top: 30px;">
                        <form class="am-form am-form-horizontal" action="../Person?type=updatePwd&&identification=0" method="post">

                            <div class="am-form-group">
                                <label for="user-name" class="am-u-sm-3 am-form-label">
                                    原密码</label>
                                <div class="am-u-sm-9">
                                    <input type="password" id="user-name" required
                                           placeholder="原密码" name="old_pwd">
                                    <small>原密码...</small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label for="user-name1" class="am-u-sm-3 am-form-label">
                                    新密码</label>
                                <div class="am-u-sm-9">
                                    <input type="text" id="user-name1" required
                                           placeholder="新密码" name="new_pwd">
                                    <small>新密码...</small>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-u-sm-9 am-u-sm-push-3">
                                    <input type="submit" class="am-btn am-btn-success" value="修改" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <footer class="admin-content-footer">
                <hr>
                <p class="am-padding-left"></p>
            </footer>
        </div>
        <!-- content end -->
    </div>
</div>						<!-- end-->
</body>
</html>
