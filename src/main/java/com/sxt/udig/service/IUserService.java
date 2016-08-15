package com.sxt.udig.service;

import com.sxt.udig.entity.User;
import com.sxt.udig.model.Pager;

public interface IUserService {

	Pager<User> findUser(User model,int currentPage,int pageSize);
	

}
