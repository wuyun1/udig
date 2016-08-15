package com.sxt.udig.entity;

import com.sxt.udig.common.BaseEntity;

public class City  extends BaseEntity {
	private String code;
	private String name;
	private String provincecode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	@Override
	public String toString() {
		return "City [code=" + code + ", name=" + name + ", provincecode="
				+ provincecode + "]";
	}
	
	
	
	
}
