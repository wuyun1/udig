package com.sxt.udig.service.impl;

import java.util.List;

import com.sxt.udig.dao.impl.UserDAO;
import com.sxt.udig.entity.User;
import com.sxt.udig.model.Pager;
import com.sxt.udig.service.IUserService;

public class UserService implements IUserService {
	
	UserDAO dao = new UserDAO();

	@Override
	public Pager<User> findUser(User model, int currentPage, int pageSize) {
		
		int mTotalRecord = dao.countByModelWithPageAndCondition(model);
		Pager<User> page = new Pager<>(pageSize,currentPage,mTotalRecord );
		List<User> queryByModelWithPage = dao.queryByModelWithPage(model, page.getCurrentPage(), page.getPageSize());
		page.setDataList(queryByModelWithPage);
		
		return page;
	}

	public Pager<User> findUserUseLike(User model, int currentPage, int pageSize) {
		Long mTotalRecord = dao.countByModelWithPageAndConditionUseLike(model);
		Pager<User> page = new Pager<>(pageSize,currentPage,mTotalRecord );
		List<User> queryByModelWithPage = dao.queryByModelWithPageUseLike(model, page.getCurrentPage(), page.getPageSize());
		page.setDataList(queryByModelWithPage);
		
		return page;
		
	}

}
