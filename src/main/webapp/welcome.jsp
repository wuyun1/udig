<%@page import="com.sxt.udig.model.UserContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String serverUrl = request.getContextPath();
	request.setAttribute("serverUrl", serverUrl);
	
	

 %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(request.getSession().getAttribute("loginUser")!=null){
		UserContext userContext = (UserContext)request.getSession().getAttribute("userContext");
		if(userContext.isPlaying())
			response.sendRedirect(serverUrl+"/playGame.action");
		else{
			response.sendRedirect(serverUrl+"/gameRoom.action");
		}
		return ;
	}
 %>
 
<!DOCTYPE html">
<html>
<head>
<meta charset="utf-8">
<title>你画我猜</title>
<link href="css/all.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="user_div">
<div>
	<img src="images/logo.jpg" width="80" height="80" />
</div>
<div>
	你画我猜
</div>
<div>
	<a id="login" href="logout">开始游戏</a>
</div>

</div>
</body>
</html>
 