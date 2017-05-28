package com.iscc.authority.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.iscc.authority.dao.EmployDao;
import com.iscc.authority.dao.UserDao;
import com.iscc.authority.dto.UserAllMessage;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;

public class EmployBiz {
	
	/**
	 * 根据部门编号获取部门下的所有员工
	 * @param deptNo
	 * @return
	 * @throws Exception
	 */
	public List<Employee> getSubEmploy(String deptNo) throws Exception{
		EmployDao emDao = new EmployDao();
		List<Employee> emList=new ArrayList<Employee>();
		try {
			emList = emDao.getSubEmploy(deptNo);
		} catch (Exception e) {
			throw e;
		}finally{
			emDao.closeConnection();
		}
		
		return emList;
	}

	
	public UserAllMessage getUserAllMessage(){
		return null;
		
	}
	
	/**
	 * 删除雇员
	 * @param emp
	 */
	public void delEmp(Employee emp) throws Exception{
		EmployDao empDao = new EmployDao();
		try {
			empDao.beginTransaction();
			empDao.delEmpTur(emp);//先删除用户-角色中间表,再删除权限-用户中间表，再删除用户表，再删除雇员表
			empDao.delEmpTea(emp);
			empDao.delTuser(emp);
			empDao.delTem(emp);
			empDao.commit();
		} catch (Exception e) {
			empDao.rollback();
			throw e;
		}finally{
			empDao.closeConnection();
		}
	}
	
	
	/**
	 * 1.更新雇员表
	 * 2.更新用户表
	 * 3.删除原有的角色
	 * 4.加入现在的角色
	 * 5.删除原有的权限
	 * 6.增加现在的权限
	 * @param employee
	 * @param user
	 * @param selectRole
	 * @param authMap
	 */
	public void updateEmploy(Employee employee,User user,List<Role> selectRoles,List<Auth> selectAuth) throws Exception{
		EmployDao employDao = new EmployDao();
		UserDao userDao = new UserDao();
		userDao.setConnection(employDao.getConnection());
		try {
			employDao.beginTransaction();
			employDao.updateEmploy(employee);//更新雇员信息
			userDao.updateUser(user);//更新用户信息
			userDao.deletRole(employee);//删除雇员原有的角色
			userDao.addUserRole(employee.getEno(),selectRoles);//重新增加雇员的角色
			userDao.deletAuth(employee);
			userDao.addAuth(employee.getEno(), selectAuth);//增加用户的权限
			employDao.commit();
		} catch (Exception e) {
			employDao.rollback();
		}
		
	}
}
