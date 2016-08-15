package com.sxt.udig.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxt.udig.common.BaseDAO;
import com.sxt.udig.dao.IProvinceDAO;
import com.sxt.udig.entity.Province;
import com.sxt.udig.util.DBHelper;

public class ProvinceDAO extends BaseDAO<Province> implements IProvinceDAO {

	@Override
	public List<Province> queryByCode(String code) {
		String sql = "select * from province where code=" + code;
		return (List<Province>) DBHelper.query(sql, null, new GetAllEntityDataHandler());
	}

	@Override
	protected Province dataToEntity(ResultSet rs) throws SQLException {
		Province province = new Province();
		province.setId(rs.getLong("id"));
		province.setCode(rs.getString("code"));
		province.setName(rs.getString("name"));
		return province;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "province";
	}



}
