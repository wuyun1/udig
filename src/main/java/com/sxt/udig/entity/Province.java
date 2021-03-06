package com.sxt.udig.entity;

import com.sxt.udig.common.BaseEntity;

public class Province  extends BaseEntity {
	private String code;
	private String name;
	
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
	@Override
	public String toString() {
		return "Province [code=" + code + ", name=" + name + "]";
	}
	
	
}
