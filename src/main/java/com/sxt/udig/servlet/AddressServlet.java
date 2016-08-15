package com.sxt.udig.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxt.udig.service.impl.AddressService;
import com.sxt.udig.util.JSONUtil;

@WebServlet("/servlet/AddressServlet")
public class AddressServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2385913163742449692L;
	AddressService addressService = new AddressService();

	/**
	 * Constructor of the object.
	 */
	public AddressServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json;  charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		
		Object result = null;
		
		switch (op) {
		case "getProvinces":
			result = getProvinces(request,response);
			break;
		case "getCitys":
			result = getCitys(request,response);
			break;
		case "getAreas":
			result = getAreas(request,response);
			break;
		case "getTime":
			result = getTime(request,response);
			break;
		default:
			result = "ERROR";
			break;
		}
		
		out.write(JSONUtil.stringify(result));
		
		out.flush();
		out.close();
	}

	private Object getTime(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new Date();
	}

	private Object getAreas(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		if(code==null){
			return null;
		}
		return addressService.getAreasByCityCode(code);
	}

	private Object getCitys(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		if(code==null){
			return null;
		}
		return addressService.getCitysByProvinceCode(code);
	}

	private Object getProvinces(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return addressService.getProvinces();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
