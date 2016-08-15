package com.sxt.udig.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sxt.udig.common.BaseDAO;
import com.sxt.udig.dao.IUserDAO;
import com.sxt.udig.entity.User;
import com.sxt.udig.util.DBHelper;

/**
 * @author Administrator
 *
 */
public class UserDAO extends BaseDAO<User> implements IUserDAO {
	


	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		User user = new User();
		user.setName("wuyun");
		user.setPasswd("1111");
		user.setEmail("222");
		dao.add(user);
		System.out.println(dao.queryAll());
		System.out.println(user);
	}

	@Override
	protected String getTableName() {
		return "user";
	}

	@Override
	public List<User> queryByModelWithPageUseLike(User model, int currentPage,
			int pageSize) {
		List<User> objs = new ArrayList<User>();
		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		modelToConditionUseLike(model, sql, values);
		sql.append(" limit ? , ?");System.out.println(sql);
		values.add((currentPage - 1) * pageSize);
		values.add(pageSize);
		return (List<User>) DBHelper.query(sql.toString(), values.toArray(), new GetAllEntityDataHandler());
	}


	@Override
	protected User dataToEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId((Long) rs.getObject("id"));
		user.setEmail(rs.getString("email"));
		user.setPhone((Long) rs.getObject("phone"));
		user.setPasswd(rs.getString("passwd"));
		user.setName(rs.getString("name"));
		return user;
	}

	@Override
	public User findUserByAccount(String account) {
		String sql = "select * from user where account=?";
        Object[] params = new Object[] { account };
        return (User) DBHelper.query(sql, params, new GetFirstEntityDataHandler());
	}

	@Override
	public User findUserByThirdId(String thirdId) {
		
		User model = new User();
		model.setThirdId(thirdId);
		return this.queryByModelAtFirst(model );
	}

	@Override
	public User insertUser(User user) {
		this.add(user);
		
		// 取回已插入user，这个user会包含数据库自增生成的id值
        if (user.getAccount() != null) {
            user = findUserByAccount(user.getAccount());
        } else if (user.getThirdId() != null) {
            user = findUserByThirdId(user.getThirdId());
        }
		
		return user;
	}


	

	

	
}
