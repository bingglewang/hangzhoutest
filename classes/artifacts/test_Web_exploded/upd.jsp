<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改</title>
</head>
<body>
	<form action="upd.action" method="post"> 
		<input type="hidden" name="user.userId" value="${userId}"/>
       	 用户名：<input type="text" name="user.userName"/><br/>  
        	订单号：<input type="text" name="user.orderId"/><br/>  
        		<input type="submit" value="修改"/>  
 </form>
</body>
</html>