package com.sxt.udig.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sxt.udig.dao.impl.UserDAO;
import com.sxt.udig.entity.User;

public class UserDaoTest {
	
	IUserDAO dao= new UserDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindUserByAccount() {
		User user = dao.findUserByAccount("ddd");
		System.out.println(user);
	}

	@Test
	public void testFindUserByThirdId() {
		User user = dao.findUserByThirdId("aabb");
		System.out.println(user);
		user = dao.findUserByThirdId("baidu_1429267468");
		System.out.println(user);
	}
	
	@Test
	public void testCountByModelWithPageAndConditionUseLike(){
		User user = dao.findUserByThirdId("baidu_1429267468");
		Long count = dao.countByModelWithPageAndConditionUseLike(user);
		System.out.println(count);
	}

	@Test
	public void testUpdateName() {
		
	}

	@Test
	public void testInsertUser() {
		
	}

}
