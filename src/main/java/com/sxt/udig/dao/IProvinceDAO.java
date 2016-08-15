package com.sxt.udig.dao;

import java.util.List;

import com.sxt.udig.common.inte.IBaseDAO;
import com.sxt.udig.entity.Province;

public interface IProvinceDAO extends IBaseDAO<Province> {

	List<Province> queryByCode(String code);

}
