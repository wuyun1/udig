package com.sxt.udig.common.inte;

import java.util.List;
import java.util.Map;

import com.sxt.udig.common.BaseEntity;

public interface IBaseDAO<T extends BaseEntity> {
	public List<T> queryAll();
	public List<T> queryWithCondition(Map<String, Object> tiaojian);
	T queryById(Integer id);
	public int countWithCondition(Map<String, Object> tiaojian);
	public int count();
	public List<T> queryWithPageAndCondition(int page,int PageSize,Map<String, Object> tiaojian);
	public List<T> queryWithPage(int page,int PageSize);
	public List<T> queryByModel(T model);
	List<T> queryByModelWithPage(T model, int page, int pageSize);
	public T add(T entity);
	public boolean update(T entity);
	int countByModelWithPageAndCondition(T model);
	Long countByModelWithPageAndConditionUseLike(T model);
	T queryByModelAtFirst(T model);
}
