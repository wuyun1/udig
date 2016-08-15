<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("appctx", application.getContextPath());
%>    
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>用户中心</title>
<link href="css/all.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="user_div">
<div>
	<img src="portrait/${loginUser.genPortrait()}" width="80" height="80" />
</div>
<div>
	${loginUser.name}，您好！
</div>
<div>
	<a id="logout" href="logout">退出</a>
</div>
<div>
	<a  href="${appctx }">首页</a>
</div>
</div>
</body>
</html>