package com.sxt.udig.websocket;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.sxt.udig.context.SessionContext;
import com.sxt.udig.entity.User;


@ServerEndpoint("/websocket/tomcatWebsocket/{userId}")
public class GameSpace {
	
//	public static ConcurrentHashMap<String,Room> 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2342340481038823031L;

	@OnMessage
	public void onMessage(String message, Session session) {

		// Print the client message for testing purposes
		
		
		System.out.println("Received: " + message);

		
		try {
			HttpSession httpSession = SessionContext.getHttpSession(session);
			String result = "";
			if(httpSession.getAttribute("loginUser")!=null){
				User user = (User) httpSession.getAttribute("loginUser");
				result=user.getName()+"，你好";
			}else{
				result = "您当前尚未登陆";
			}
			session.getBasicRemote().sendText(result );
			session.getBasicRemote().sendText("您你刚才说的话："+message );
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@OnOpen
	public void onOpen(Session session,@PathParam("userId") String userId) {
		
		HttpSession httpSession = SessionContext.getHttpSession(session);
		String result = "";
		if(httpSession.getAttribute("loginUser")!=null){
			User user = (User) httpSession.getAttribute("loginUser");
			result=user.getName()+"，你好";
			if(user.getId().toString().equals(userId)){
				result="\n您的身份认证成功，身份编号为："+userId;
			}else{
				result="\n您的身份认证失败，非法ID："+userId;
			}
			
		}else{
			result = "您当前尚未登陆";
		}
		try {
			session.getBasicRemote().sendText(result );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接成功， ID：" + session.getClass().getName()+" HasCode:"+session.hashCode());
		
	}

	@OnError
	public void error(Session session, Throwable t) {
		t.printStackTrace();

	}

	@OnClose
	public void onClose(Session session) {
		String msg = "关闭连接， ID：" + session.getId()+" HasCode:"+session.hashCode();
		System.out.println(msg);
	}
	
	
	

}
