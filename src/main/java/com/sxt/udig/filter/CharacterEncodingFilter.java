package com.sxt.udig.filter;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.naming.Context;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;

import org.apache.james.protocols.api.Request;

import com.sxt.udig.context.ResponseContext;

@WebFilter(urlPatterns={"/*"},initParams={@WebInitParam(name="encoding",value="UTF-8")},filterName="CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

	String encodingString = "UTF-8";
	

	public void destroy() {
		encodingString = "";
	}
	
	public void doFilter(ServletRequest request, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		if (encodingString != null) {
			// 注意MyEclipse的版本不同，方法doFilter的参数有可能不同，
			// 设置字符编码，认准其类型为ServletRequest即可
			// 我用的是MyEclipse10
			request.setCharacterEncoding(encodingString);
			arg1.setCharacterEncoding(encodingString);
			arg1.setContentType("text/html; charset="+encodingString);
		}
		
		ResponseContext.setCurrent((HttpServletResponse) arg1);
		arg2.doFilter(request, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 注意MyEclipse的版本不同，方法init的参数有可能不同
		// 这里的参数为arg0
		// 我用的是MyEclipse10
		// 从配置文件中取编码
		this.encodingString = arg0.getInitParameter("encoding").trim();
		if (encodingString == null) {
			encodingString = "UTF-8";
		}
	}

}
