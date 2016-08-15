package com.sxt.udig.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.sxt.udig.common.inte.IBaseDAO;
import com.sxt.udig.util.DBHelper;
import com.sxt.udig.util.DataHandler;



public abstract class BaseDAO<T extends BaseEntity> implements IBaseDAO<T> {

	protected abstract T dataToEntity(ResultSet rs) throws SQLException;
	
	protected  class GetFirstEntityDataHandler implements DataHandler {


		public GetFirstEntityDataHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
        public <T> T handle(ResultSet rs) throws SQLException {
            T entity = null;
            if (rs.next()) {
            	entity = (T) dataToEntity(rs);
            }
            return entity;
        }

		
    }
	
	
	protected  class GetAllEntityDataHandler implements DataHandler {

        public GetAllEntityDataHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
        public <T> T handle(ResultSet rs) throws SQLException {
            List<Object> entitys = new ArrayList<>();
            while (rs.next()) {
            	entitys.add(dataToEntity(rs));
            }
            return (T) entitys;
        }

		
    }
	
	protected  class GetCountNumDataHandler implements DataHandler {

        @Override
        public <T> T handle(ResultSet rs) throws SQLException {
            if (rs.next()) {
            	return (T ) rs.getObject(1);
            }
            return null;
        }

		
    }

	@Override
	public List<T> queryAll() {

		String sql = "select * from " + this.getTableName();

		return (List<T>) DBHelper.query(sql, null, new GetAllEntityDataHandler());
		

	}

	protected void mapToCondition(Map<String, Object> tiaojian,
			StringBuilder sql, List<Object> values) {
		if(tiaojian == null || tiaojian.isEmpty()){
			return;
		}
		for (Entry<String, Object> entry : tiaojian.entrySet()) {
			sql.append(" and ").append(entry.getKey()).append(" = ?");
			values.add(entry.getValue());
		}
		sql.append(' ');
	}
	
	protected void modelToConditionUseLike(T model, StringBuilder sql,
			List<Object> values) {
		if(model == null)
			return ;
		Class c = model.getClass();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get")
					&& method.getParameterTypes().length == 0
					&& !mName.endsWith("getClass")) {
				try {
					Object v = method.invoke(model, null);
					if (v != null) {
						sql.append(" and ").append(mName.substring(3))
								.append(" like '%"+v+"%'");
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	protected void modelToCondition(T model, StringBuilder sql,
			List<Object> values) {
		if(model == null)
			return ;
		Class c = model.getClass();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get")
					&& method.getParameterTypes().length == 0
					&& !mName.endsWith("getClass")) {
				try {
					Object v = method.invoke(model, null);
					if (v != null) {
						sql.append(" and ").append(mName.substring(3))
								.append(" = ?");
						values.add(v);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public List<T> queryWithCondition(Map<String, Object> tiaojian) {
		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");
		List<Object> values = new ArrayList<Object>();
		mapToCondition(tiaojian, sql, values);
		return (List<T>) DBHelper.query(sql.toString(), values.toArray(), new GetAllEntityDataHandler());
	}

	@Override
	public T queryById(Integer id) {

		String sql = "select * from " + this.getTableName() + " where id=" + id;
		return (T) DBHelper.query(sql, null, new GetFirstEntityDataHandler());
	}

	protected abstract String getTableName();

	@Override
	public int countWithCondition(Map<String, Object> tiaojian) {

		StringBuilder sql = new StringBuilder("select count(*) countnum from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		mapToCondition(tiaojian, sql, values);
		return (int) DBHelper.query(sql.toString(), values.toArray(), new GetCountNumDataHandler());
	}

	@Override
	public int count() {

		String sql = "select count(*) countnum from " + this.getTableName();
		return (int) DBHelper.query(sql, null, new GetCountNumDataHandler());
	}

	@Override
	public List<T> queryWithPageAndCondition(int page, int pageSize,
			Map<String, Object> tiaojian) {

		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		mapToCondition(tiaojian, sql, values);
		sql.append(" limit ?,?");
		values.add((page - 1) * pageSize);
		values.add(pageSize);
		return (List<T>) DBHelper.query(sql.toString(), null, new GetAllEntityDataHandler());
	}

	@Override
	public List<T> queryWithPage(int page, int pageSize) {

		String sql = "select * from " + this.getTableName() + " limit ?,?";
		return  (List<T>) DBHelper.query(sql, new Object[] {
				(page - 1) * pageSize, pageSize },new GetAllEntityDataHandler());
	}

	@Override
	public List<T> queryByModel(T model) {
		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");
		List<Object> values = new ArrayList<Object>();
		modelToCondition(model, sql, values);
		return (List<T>) DBHelper.query(sql.toString(), values.toArray(), new GetAllEntityDataHandler());
	}
	
	@Override
	public T queryByModelAtFirst(T model) {
		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");
		List<Object> values = new ArrayList<Object>();
		modelToCondition(model, sql, values);
		return (T) DBHelper.query(sql.toString(), values.toArray(), new GetFirstEntityDataHandler());
	}

	@Override
	public List<T> queryByModelWithPage(T model, int page, int pageSize) {
		List<T> objs = new ArrayList<T>();
		StringBuilder sql = new StringBuilder("select * from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		modelToCondition(model, sql, values);
		sql.append(" limit ? , ?");
		values.add((page - 1) * pageSize);
		values.add(pageSize);
		return (List<T>) DBHelper.query (sql.toString(), values.toArray(), new GetAllEntityDataHandler());
	}

	public T add(T entity) {
		List<Object> values = new ArrayList<Object>();
		List<String> fileds = new ArrayList<String>();
		Class c = entity.getClass();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get")
					&& method.getParameterTypes().length == 0
					&& !mName.endsWith("getClass")) {
				try {
					Object v = method.invoke(entity, null);
					if (v != null) {
						fileds.add(mName.substring(3));
						values.add(v);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		StringBuilder sql = new StringBuilder("insert into ")
				.append(this.getTableName()).append("(")
				.append(StringUtils.join(fileds, ",")).append(") values(")
				.append(StringUtils.repeat("?", ",", fileds.size()))
				.append(")");

		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = DBHelper.getConnection();
			pstat = conn.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			for (int i = 1, n = values.size(); i <= n; i++) {
				pstat.setObject(i, values.get(i-1));
			}
			int count = pstat.executeUpdate();

			ResultSet generatedKeys = pstat.getGeneratedKeys();
			generatedKeys.next();
			entity.setId(generatedKeys.getLong(1));
			generatedKeys.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		// int count = DBUtil.executeUpdate(sql.toString(), values);
		return entity;
//		return null;

	}
	
	public boolean update(T entity){
		
		
		return false;
	}
	
	@Override
	public Long countByModelWithPageAndConditionUseLike(T model) {
		StringBuilder sql = new StringBuilder("select count(*) countnum from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		modelToConditionUseLike(model, sql, values);
		System.out.println(sql);
		return  (Long) DBHelper.query(sql.toString(), values.toArray(),new GetCountNumDataHandler());
	}

	
	@Override
	public int countByModelWithPageAndCondition(T model) {
		StringBuilder sql = new StringBuilder("select count(*) countnum from "
				+ this.getTableName() + " where 1=1");

		List<Object> values = new ArrayList<Object>();
		modelToCondition(model, sql, values);
		return  (int) DBHelper.query(sql.toString(), values.toArray(),new GetCountNumDataHandler());
	}

}
