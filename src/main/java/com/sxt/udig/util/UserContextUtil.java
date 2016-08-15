package com.sxt.udig.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.alibaba.druid.util.Base64;
import com.sxt.udig.context.LoginUserCache;
import com.sxt.udig.context.ResponseContext;
import com.sxt.udig.entity.User;
import com.sxt.udig.model.UserContext;

public class UserContextUtil {

	static public Map<Object, UserContext> userContextMap = new HashMap<Object, UserContext>();

	public static void logout(HttpSession session) {

		Object objLoginUser = session.getAttribute("loginUser");
		Long userId = null;
		if (objLoginUser != null) {
			User user = (User) objLoginUser;
			userId = user.getId();
			UserContext context = userContextMap.get(user.getId());

			Session socketSession = context.getSocketSession();
			if (socketSession != null) {
				try {
					socketSession.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			userContextMap.remove(user.getId());
			session.removeAttribute("loginUser");
			session.removeAttribute("userContext");
		}
		LoginUserCache.remove(userId);

	}

	public static void login(HttpSession session, User user) {
		session.setAttribute("loginUser", user);
		UserContext context = new UserContext();
		context.setUser(user);
		context.setHttpSession(session);
		userContextMap.put(user.getId(), context);
		session.setAttribute("userContext", context);
		LoginUserCache.put(user);
	}

	public static boolean isLogining(HttpSession httpSession) {
		return httpSession.getAttribute("loginUser") == null;
	}

	
	

}
