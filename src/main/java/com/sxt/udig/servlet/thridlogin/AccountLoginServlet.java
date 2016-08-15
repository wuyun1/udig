package com.sxt.udig.servlet.thridlogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxt.udig.context.LoginUserCache;
import com.sxt.udig.dao.impl.UserDAO;
import com.sxt.udig.entity.User;
import com.sxt.udig.model.UserContext;
import com.sxt.udig.util.OAuthHelper;
import com.sxt.udig.util.UserContextUtil;

/**
 * 用户使用本地账号登录
 */
@WebServlet("/login")
public class AccountLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    /**
     * post请求执行登录验证
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String account = request.getParameter("account");
        String passwd = request.getParameter("passwd");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByAccount(account.trim());
        if (user == null || !passwd.equals(user.getPasswd())) {
            request.setAttribute("errMsg", "用户名或密码错误");
            request.setAttribute("account", account);
            request.setAttribute("baiduAuthUrl", OAuthHelper.getInfo("baidu").getAuthUrl());
            request.setAttribute("renrenAuthUrl", OAuthHelper.getInfo("renren").getAuthUrl());
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        } else {
            UserContextUtil.login(request.getSession(),user);
            response.sendRedirect("user_page"); // 跳转到用户主页
        }
    }

    /**
     * Get请求转到login.jsp
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	User user = LoginUserCache.getUserFormCookie(request);
    	
    	if(user != null ){
    		HttpSession session = request.getSession();
    		if(UserContextUtil.isLogining(session)){
    			UserContextUtil.logout(session);
    		}
    		UserContextUtil.login(session, user);
    		response.sendRedirect("user_page"); // 跳转到用户主页
    		return;
    	}
    	
    	request.setAttribute("baiduAuthUrl", OAuthHelper.getInfo("baidu").getAuthUrl());
        request.setAttribute("renrenAuthUrl", OAuthHelper.getInfo("renren").getAuthUrl());

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

}
