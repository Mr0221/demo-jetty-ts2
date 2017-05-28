package com.iscc.authority.biz;

import java.util.List;
import java.util.Map;

import com.iscc.authority.dao.EmployDao;
import com.iscc.authority.dao.UserDao;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;

public class AddBiz {

	/**
	 * 1.雇员表
	 * 2.用户表 
	 * 3.用户角色表
	 * 4.用户权限
	 * 
	 * @param employee
	 * @param user
	 * @param selectRole
	 * @param authMap
	 * @throws Exception
	 */
	public void add(Employee employee,User user,List<Role> selectRole,List<Auth> selectAuth) throws Exception{
		EmployDao employDao = new EmployDao();
		UserDao userDao = new UserDao();
		userDao.setConnection(employDao.getConnection());
		try {
			employDao.beginTransaction();
			employDao.addEmploy(employee);
			userDao.addUser(user);
			userDao.addUserRole(user.getEno(), selectRole);
			userDao.addAuth(user.getEno(), selectAuth);
			employDao.commit();
		} catch (Exception e) { 
			e.printStackTrace();
			employDao.rollback();
			throw e;
		}finally{
			employDao.closeConnection();
		}
		
	}
}
