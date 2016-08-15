package com.sxt.udig.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sxt.udig.context.SessionContext;

@WebListener
public class SessionContextListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		SessionContext.add(se.getSession());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionContext.remove(se.getSession());
	}

}
