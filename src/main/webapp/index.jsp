<%@page import="com.sxt.udig.model.UserContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String serverUrl = request.getContextPath();
	request.setAttribute("serverUrl", serverUrl);
	
	String wsUrl = request.getServerName()
			+ (request.getServerPort() == 80 ? "" : ":"
					+ request.getServerPort())
			+ serverUrl;
	request.setAttribute("wsUrl", wsUrl);

 %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	if(request.getSession().getAttribute("loginUser")==null){
		response.sendRedirect(serverUrl+"/welcome.jsp");
	}else{
		UserContext userContext = (UserContext)request.getSession().getAttribute("userContext");
		if(userContext.isPlaying())
			response.sendRedirect(serverUrl+"/playGame.action");
		else{
			response.sendRedirect(serverUrl+"/gameRoom.action");
		}
	}
 %>