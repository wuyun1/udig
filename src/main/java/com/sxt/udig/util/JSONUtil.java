package com.sxt.udig.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxt.udig.entity.User;


public class JSONUtil {
	
	static ObjectMapper  mapper = new ObjectMapper();  
	public static String stringify(Object pager) {
		try {
			return mapper.writeValueAsString(pager).toString();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		User user = new User();
		user.setName("asdfsadf");
		user.setEmail("842269153");
		System.out.println(stringify(user));
	}

}
