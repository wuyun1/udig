package com.realtime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.realtime.flash.FlashSecurityServer;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class StartSingleAppServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		new FlashSecurityServer(843).start();
		System.out.println("flash server 843 start ...");


	}
}
