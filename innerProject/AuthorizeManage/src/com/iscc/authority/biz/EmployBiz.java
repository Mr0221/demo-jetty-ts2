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
	 * ���ݲ��ű�Ż�ȡ�����µ�����Ա��
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
	 * ɾ����Ա
	 * @param emp
	 */
	public void delEmp(Employee emp) throws Exception{
		EmployDao empDao = new EmployDao();
		try {
			empDao.beginTransaction();
			empDao.delEmpTur(emp);//��ɾ���û�-��ɫ�м��,��ɾ��Ȩ��-�û��м����ɾ���û�����ɾ����Ա��
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
	 * 1.���¹�Ա��
	 * 2.�����û���
	 * 3.ɾ��ԭ�еĽ�ɫ
	 * 4.�������ڵĽ�ɫ
	 * 5.ɾ��ԭ�е�Ȩ��
	 * 6.�������ڵ�Ȩ��
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
			employDao.updateEmploy(employee);//���¹�Ա��Ϣ
			userDao.updateUser(user);//�����û���Ϣ
			userDao.deletRole(employee);//ɾ����Աԭ�еĽ�ɫ
			userDao.addUserRole(employee.getEno(),selectRoles);//�������ӹ�Ա�Ľ�ɫ
			userDao.deletAuth(employee);
			userDao.addAuth(employee.getEno(), selectAuth);//�����û���Ȩ��
			employDao.commit();
		} catch (Exception e) {
			employDao.rollback();
		}
		
	}
}
