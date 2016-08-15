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
		response.sendRedirect(serverUrl+"/login");
		return;
	}
 %>
<!DOCTYPE html>
<html>
<head>
<title>你画我猜JavaWeb版</title>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/web_socket.js"></script>
<script type="text/javascript">
	// Set URL of your WebSocketMain.swf here:
	WEB_SOCKET_SWF_LOCATION = "js/WebSocketMain.swf";
	// Set this to dump debug message from Flash to console.log:
	WEB_SOCKET_DEBUG = true;

	// Everything below is the same as using standard WebSocket.
	var socket;

	function init() {
		socket = new WebSocket("ws://${wsUrl}/websocket/tomcatWebsocket/${loginUser.id}");
		socket.onmessage = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = ta.value + '\n' + event.data
			console.log(event);
		};
		socket.onopen = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = "Web Socket opened!";
			console.log(event);
		};
		socket.onclose = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = ta.value + "Web Socket closed";
			console.log(event);
		};
		socket.onerror = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = "Web Socket Error!";
			console.log(event);
		};
	}

	function send(message) {
		if (!window.WebSocket) {
			return;
		}
		if (socket.readyState == WebSocket.OPEN) {
			socket.send(message);
		} else {
			alert("The socket is not open.");
		}
	}
	
	init();
</script>
</head>
<body >
	<form>
		<input type="text" name="message" value="Hello, World!" /><input
			type="button" value="Send Web Socket Data"
			onclick="send(this.form.message.value)" />
		<h3>Output</h3>
		<textarea id="responseText" style="width: 100%; height: 300px;"></textarea>
	</form>
	
	<a href="/${serverUrl}/gameRoom.action" target="_self" >开始游戏</a>
</body>
</html>