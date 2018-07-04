<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="favicon.ico" rel="shortcut icon" />
    <title>咸亨科研中心测试demo</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 显示/隐藏菜单 -->
    <div class="layui-header" style="background-color: #2F4056">
        <div class="layui-logo" style="margin-left: 45%;"><h2 style="color: azure;font-family: 'Microsoft YaHei';width: 260px;">咸亨科研中心测试demo</h2></div>
    </div>

    <div class="layui-side layui-bg-black" >
        <div class="layui-side-scroll">
            <div title="菜单缩放" class="kit-side-fold" style="text-align: center;width: 200px;line-height: 36px;"> <i class="layui-icon layui-icon-home"  style="font-size: 30px;" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed" >
                    <a class="" href="javascript:;" ><i class="layui-icon layui-icon-user" ></i> <span >用户信息管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=request.getContextPath()%>/page/user/userList.html" target="testDemo"><i class="layui-icon layui-icon-user"></i> <span >用户列表</span></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon layui-icon-user"></i> <span >班组人员管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="<%=request.getContextPath()%>/page/teamPeople/teamPeopleList.html" target="testDemo"><i class="layui-icon layui-icon-user"></i> <span >班组人员列表</span></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
            <iframe id="testDemo" name="testDemo" src="<%=request.getContextPath()%>/user/addUI.action" style="overflow: visible;"
                    width="100%" height="100%" scrolling="no" frameborder="0">
            </iframe>
       <%-- onload="this.height=testDemo.document.body.scrollHeight"--%>
    </div>

    <div class="layui-footer" style="text-align: center">
        <!-- 底部固定区域 -->
        © binggleW 王炳贵的demo
    </div>
</div>




<script src="js/index.js"></script>
</body>
</html>