package com.sxt.udig.service;

import java.util.List;

import com.sxt.udig.entity.Area;
import com.sxt.udig.entity.City;
import com.sxt.udig.entity.Province;

public interface IAddressService {

	List<Area> getAreasByCityCode(String code);

	List<City> getCitysByProvinceCode(String code);

	List<Province> getProvinces();
	


}
