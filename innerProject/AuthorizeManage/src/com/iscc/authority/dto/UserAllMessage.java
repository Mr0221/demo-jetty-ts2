package com.iscc.authority.dto;

import java.util.List;

import com.iscc.authority.dao.AuthDao;
import com.iscc.authority.dao.EmployDao;
import com.iscc.authority.dao.RoleDao;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;

public class UserAllMessage {

	private Employee emp;
	private List<Role> roleList;
	private List<Auth> authList;
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Auth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<Auth> authList) {
		this.authList = authList;
	}

	/*public UserAllMessage getUserAllMessage(String empNo){
		EmployDao employDao = new EmployDao();
		RoleDao roleDao = new RoleDao();
		emp = employDao.getEmp(empNo);
		roleList = roleDao.getRole(empNo);
		AuthDao authDao = new AuthDao();
		authList = authDao.getAuth(roleList);
		
		return null;
	}*/
}
