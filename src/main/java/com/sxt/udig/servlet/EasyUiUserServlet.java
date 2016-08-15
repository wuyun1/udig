package com.sxt.udig.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.sxt.udig.entity.User;
import com.sxt.udig.model.Pager;
import com.sxt.udig.service.impl.UserService;
import com.sxt.udig.util.JSONUtil;

@WebServlet("/user.action")
public class EasyUiUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5652919241645497079L;
	UserService userService;

	/**
	 * Constructor of the object.
	 */
	public EasyUiUserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/admin/userlist.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.flush();
		// out.close();
		String op = request.getParameter("op");

		switch (op) {
		case "getUsers":
			getUsers(request, response);
			break;

		default:
			errorPage(request, response);
			break;
		}

	}
	
	private void getUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String pageStr = StringUtils.defaultString(
				request.getParameter("page"), "1");
		String pageSizeStr = StringUtils.defaultString(
				request.getParameter("rows"), "4");
		String searchNameStr = StringUtils.defaultIfEmpty(
				request.getParameter("searchName"), null);
		String searchIdStr = StringUtils.defaultIfEmpty(
				request.getParameter("searchId"), null);

		try {
			int currentPage = Integer.parseInt(pageStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			Long searchIdInt = null;
			try {
				searchIdInt = Long.parseLong(searchIdStr);
			} catch (Exception e) {
				// TODO: handle exception
			}
			User model = new User();
			model.setName(searchNameStr);
			model.setId(searchIdInt);
			Pager<User> pager = userService.findUserUseLike(model, currentPage, pageSize);
			
			response.setContentType("text/json;  charset=utf-8");
			PrintWriter out = response.getWriter();
			Map result = new HashMap<String, Object>();
			result.put("total", pager.getTotalRecord());
			result.put("rows", pager.getDataList());
			out.write(JSONUtil.stringify(result));
			out.flush();
			out.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			errorPage(request, response, e);
		}

		
	}

	
	private void errorPage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Error Operate:" + request.getParameter("op"));
		out.flush();
		out.close();
	}

	private void errorPage(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Error:" + e.getMessage());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		userService = new UserService();
	}

}
