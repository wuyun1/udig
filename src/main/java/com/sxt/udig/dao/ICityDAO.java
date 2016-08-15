package com.sxt.udig.dao;

import java.util.List;

import com.sxt.udig.common.inte.IBaseDAO;
import com.sxt.udig.entity.City;

public interface ICityDAO extends IBaseDAO<City> {

	List<City> queryByProvinceCode(String code);

}
