package com.sxt.udig.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxt.udig.common.BaseDAO;
import com.sxt.udig.dao.IAreaDAO;
import com.sxt.udig.entity.Area;
import com.sxt.udig.util.DBHelper;

public class AreaDAO extends BaseDAO<Area> implements IAreaDAO {
	
	@Override
	protected Area dataToEntity(ResultSet rs) throws SQLException {
		Area area = new Area();
		area.setId(rs.getLong("id"));
		area.setCode(rs.getString("code"));
		area.setCitycode(rs.getString("citycode"));
		area.setName(rs.getString("name"));
		return area;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "area";
	}

	@Override
	public List<Area> queryByCityCode(String code) {
		Area model = new Area();
		model.setCitycode(code);
		return this.queryByModel(model);
	}

}
