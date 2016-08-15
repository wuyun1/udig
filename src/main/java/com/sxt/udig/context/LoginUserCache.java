package com.sxt.udig.context;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.util.Base64;
import com.sxt.udig.entity.User;

public class LoginUserCache {
	
	//秒
	static int expire = 1800;
	
	private static Map<Object,LoginUser> cache = new HashMap<Object,LoginUser>();
	
	private static class LoginUser {
		private long expire;
		private User user;
		public long getExpire() {
			return expire;
		}
		public void setExpire(long expire) {
			this.expire = expire;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
	}
	
	
	private static ThreadLocal<User> tl = new ThreadLocal<>();



	public static void setCurrent(User user) {
		if (user != null) {
			tl.set(user);
		} else {
			tl.remove();
		}
	}

	public static User getCurrent() {
		return tl.get();
	}


	
	public static void put(User user) {
		
		LoginUser loginUser = cache.get(user.getId());
		if(loginUser==null){
			loginUser= new LoginUser();
		}
		loginUser.setExpire(expire+  Calendar.getInstance().getTime().getTime());
		loginUser.setUser(user);
		cache.put(user.getId(),loginUser);
		setCurrent(user);
		setCookie(user);
	}
	
	public static User getUserFormCookie(HttpServletRequest request) {
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		if(cookies !=null ){
			for (Cookie cookie : cookies) {
				if("auth".equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		
		byte[] result = Base64.base64ToByteArray(cookieValue);
		String auth = new String(result);
		
		String[] array = auth.split("\\$");
		if(2==array.length) {
			User user = LoginUserCache.get(Long.parseLong(array[0]));
			if(null != user && Objects.equals(user.getPasswd(), array[1]) ){
				return user;
			}
		}
		return null;
	}
	
	public static void setCookie(User user) {

		String source = user.getId()+"$" + user.getPasswd();
		String result = Base64.byteArrayToBase64(source.getBytes());
		Cookie cookie = new Cookie("auth",result);
		cookie.setMaxAge(expire);
		ResponseContext.getCurrent().addCookie(cookie);
		
	}
	
	public static void remove(Long userId) {
		cache.remove(userId);
		Cookie cookie = new Cookie("auth", null);
		ResponseContext.getCurrent().addCookie(cookie);
		setCurrent(null);
	}

	public static User get(Long userId) {
		LoginUser loginUser = cache.get(userId);
		if(loginUser!=null){
			if(loginUser.getExpire()>Calendar.getInstance().getTime().getTime()){
				loginUser.setExpire(expire+  Calendar.getInstance().getTime().getTime());
				return loginUser.user;
			}else{
				remove(loginUser.user.getId());
			}
		}
		return null;
	}
}
