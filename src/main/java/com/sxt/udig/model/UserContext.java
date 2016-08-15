package com.sxt.udig.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sxt.udig.entity.User;

public class UserContext {
	
	
	private Session socketSession ;
	private boolean playing;
	private HttpSession httpSession;
	private User user;
	
	
	
	public Session getSocketSession() {
		return socketSession;
	}
	public void setSocketSession(Session socketSession) {
		this.socketSession = socketSession;
	}
	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
