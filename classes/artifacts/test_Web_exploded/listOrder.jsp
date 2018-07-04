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
	<!-- 订单号&nbsp;&nbsp;   
        订单名称  &nbsp;&nbsp;       
         -->
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
	<br>
	<br>
	
	<table border="1" cellspacing="0" align="center"  width="400">		
	<thead>
		<tr>
	      <th>订单号</th>
	      <th>订单名称</th>	    
	    </tr>
	</thead>
			<tr>
			    <td align="center">${order.orderId}&nbsp;&nbsp;</td>
				<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;${order.orderName}</td>				
				</td>
			</tr>		
	</table>
</body>
</html>