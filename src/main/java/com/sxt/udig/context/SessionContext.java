package com.sxt.udig.context;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class SessionContext {
	
	static Map<String,HttpSession> httpSessionContext = new HashMap<>();

	public static void add(HttpSession session) {
		
		httpSessionContext.put(session.getId(), session);
		
	}

	public static void remove(HttpSession httpSession) {
		httpSessionContext.remove(httpSession.getId());
	}
	
	public static HttpSession get(String sessionId) {
		return httpSessionContext.get(sessionId);
	}
	
	static public Session getSocketSession(String httpSessionid){
		HttpSession hs = httpSessionContext.get(httpSessionid);
		if(hs==null){
			return null;
		}
		Object attribute = hs.getAttribute("socketSession");
		if(attribute==null)
			return null;
		return  (Session) attribute;
	}
	
	static public HttpSession getHttpSession(Session socketSession){
		Class<? extends Session> c = socketSession.getClass();
		Field f;
		try {
			f = c.getDeclaredField("httpSessionId");
			f.setAccessible(true);
			Object result = f.get(socketSession);
			return  httpSessionContext.get(result.toString());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
