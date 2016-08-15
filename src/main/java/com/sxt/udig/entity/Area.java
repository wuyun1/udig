package com.sxt.udig.entity;

import com.sxt.udig.common.BaseEntity;

public class Area  extends BaseEntity {
	private String code;
	private String name;
	private String citycode;
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
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	@Override
	public String toString() {
		return "Area [code=" + code + ", name=" + name + ", citycode="
				+ citycode + "]";
	}
	
	
}
