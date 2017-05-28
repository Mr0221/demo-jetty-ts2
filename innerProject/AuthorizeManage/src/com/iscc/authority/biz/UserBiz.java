package com.iscc.authority.biz;

import java.util.List;
import java.util.Map;

import com.iscc.authority.dao.UserDao;
import com.iscc.authority.entity.Department;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;
import com.iscc.authority.my.tree1;

public class UserBiz {
	/**
	 * 添加到用户-角色表中
	 * @param selectRole
	 * @throws Exception
	 */
	public void addRole(String eNo,List<Role> selectRole) throws Exception{
		UserDao userDao = new UserDao();
		try {
			userDao.addUserRole(eNo,selectRole);
		} catch (Exception e) {
			throw e;
		}finally{
			userDao.closeConnection();
		}
		
	}	

	
	public User getUser(String eNo) throws Exception{
		UserDao userDao = new UserDao();
		User user = null;
		try {
			user = userDao.getUserById(eNo);
		} catch (Exception e) {
			throw e;
		}finally{
			userDao.closeConnection();
		}
		
		return user;
	}
	
}
