<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户列表</title>
</head>
<body>

 <div>
<form > 
	<!-- 用户id&nbsp;&nbsp;   
        用户名  &nbsp;&nbsp;   -->     
      <!--  订单号  -->
 </form>
</div>
	<!-- <s:iterator value="list" status="menu">
		<s:property value="#menu.name"/>
		<s:property value="#menu.password"/>
	</s:iterator>
	-->
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<table border="1" cellspacing="0" align="center" width="400">
	<thead>
		<tr>
	      <th>用户id</th>
	      <th>用户名</th>
	      <th> 订单号</th>
			<th> 订单名称</th>
	      <th>操作</th>
	    </tr>
	</thead>
	
		<c:forEach items="${list}" var="user1" >
			<tr>
			    <td align="center">${user1.userId} </td>
				<td align="center">${user1.userName} </td>
				<td align="center"><a href="../order/userorder?orderId=${user1.orderId}">${user1.orderId}</a></td>				
				<td align="center">${user1.orderName}</td>
				<td align="center" >
				<a href="updUI?userId=${user1.userId}">修改</a>
				<a href="del?user.userId=${user1.userId}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr><td colspan="4" align="center"><a href="user/addUI">添加</a></td></tr>
	</table>
	
</body>
</html>