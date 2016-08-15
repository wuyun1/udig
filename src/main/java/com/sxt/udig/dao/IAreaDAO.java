package com.sxt.udig.dao;

import java.util.List;

import com.sxt.udig.common.BaseEntity;
import com.sxt.udig.common.inte.IBaseDAO;
import com.sxt.udig.entity.Area;

public interface IAreaDAO extends IBaseDAO<Area>{

	List<Area> queryByCityCode(String code);

}
