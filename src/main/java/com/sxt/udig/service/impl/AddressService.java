package com.sxt.udig.service.impl;

import java.util.List;

import com.sxt.udig.dao.IAreaDAO;
import com.sxt.udig.dao.ICityDAO;
import com.sxt.udig.dao.IProvinceDAO;
import com.sxt.udig.entity.Area;
import com.sxt.udig.entity.City;
import com.sxt.udig.entity.Province;
import com.sxt.udig.service.IAddressService;

public class AddressService implements IAddressService{
	private IAreaDAO areaDAO = new AreaDAO();
	private ICityDAO cityDAO = new CityDAO();
	private IProvinceDAO provinceDAO = new ProvinceDAO();
	
	@Override
	public List<City> getCitysByProvinceCode(String code){
		return cityDAO.queryByProvinceCode(code);
	}
	
	@Override
	public List<Area> getAreasByCityCode(String code){
		return areaDAO.queryByCityCode(code);
	}
	
	@Override
	public List<Province> getProvinces(){
		return provinceDAO.queryAll();
	}


	
}
