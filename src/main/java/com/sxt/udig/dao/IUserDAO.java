package com.sxt.udig.dao;

import java.util.List;

import com.sxt.udig.common.inte.IBaseDAO;
import com.sxt.udig.entity.User;

public interface IUserDAO extends IBaseDAO<User>{
	
	/**
     * 按account查询user
     * 
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     * 分页模糊查询
     * @param model 查询的参照数据模型
     * @param currentPage 当前页面
     * @param pageSize 分页大小
     * @return
     */
	List<User> queryByModelWithPageUseLike(User model, int currentPage,
			int pageSize);
	
	 /**
     * 按third_id查找user
     * 
     * @param thirdId
     * @return
     */
    User findUserByThirdId(String thirdId);
    
    User insertUser(User user);
    
    

}
