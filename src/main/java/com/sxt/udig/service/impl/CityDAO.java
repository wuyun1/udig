package com.sxt.udig.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxt.udig.common.BaseDAO;
import com.sxt.udig.dao.ICityDAO;
import com.sxt.udig.entity.City;
import com.sxt.udig.util.DBHelper;

public class CityDAO extends BaseDAO<City> implements ICityDAO {

	@Override
	public List<City> queryByProvinceCode(String code) {
		String sql = "select * from city where provincecode=" + code;
		return (List<City>) DBHelper.query(sql, null, new GetAllEntityDataHandler());
	}

	@Override
	protected City dataToEntity(ResultSet rs) throws SQLException {
		City city = new City();
		city.setId(rs.getLong("id"));
		city.setCode(rs.getString("code"));
		city.setName(rs.getString("name"));
		return city;
	}

	@Override
	protected String getTableName() {
		return "city";
	}

	

}
