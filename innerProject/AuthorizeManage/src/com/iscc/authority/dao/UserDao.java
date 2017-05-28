package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Department;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;

public class UserDao extends BaseDao {

	/**
	 * 添加用户
	 * 
	 * @param eNo
	 * @param uname
	 * @param pwd
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		String sql = "insert into tuser values('" + user.getEno() + "','"
				+ user.getUname() + "','" + user.getPwd() + "')";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);

		int i = ps.executeUpdate();
		if (i > 0) {
			System.out.println("添加用户信息成功");
		}
	}

	/**
	 * 将选择的角色加入用户-角色表中
	 * 
	 * @param eNo
	 * @param selectRole
	 * @throws Exception
	 */
	public void addUserRole(String eNo, List<Role> selectRoles)	throws Exception {
		this.OpenConncetion();
		for (int i = 0; i < selectRoles.size(); i++) {
			Role role = selectRoles.get(i);
			int rId = role.getId();
			String sql = "insert into tuserrole values((select nvl(max(id),0)+1 from tuserrole),'"+ eNo + "','" + rId + "')";
			PreparedStatement ps = connection.prepareStatement(sql);
			try {
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * 将选择的权限加到"用户-权限"表中
	 * 
	 * @param eNo
	 * @param authMap
	 */
	public void addAuth(String eNo, List<Auth> selectAuth)throws Exception {
		this.OpenConncetion();		
		for (Auth auth : selectAuth) {
			String sql = "insert into temployauth values((select nvl(max(id),0)+1 from temployauth),'"
					+ eNo + "','" + auth.getaId() + "')";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
		}
	}

	/**
	 * 根据empNo查询用户信息
	 * 
	 * @param eNo
	 */
	public User getUserById(String eNo) throws Exception {
		String sql = "select *from tuser where eno='" + eNo + "'";
		User user = new User();
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			user.setEno(rs.getString("eno"));
			user.setUname(rs.getString("uname"));
			user.setPwd(rs.getString("pwd"));
		}

		return user;
	}

	/**
	 * 更新用户信息成功
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception {
		String sql = "update tuser set uname='" + user.getUname() + "',pwd='"
				+ user.getPwd() + "' where eno='" + user.getEno() + "'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		
		
	}

	/**
	 * 更新雇员信息之前，删除雇员的原有角色
	 * 
	 * @param employee
	 * @throws Exception
	 */
	public void deletRole(Employee employee) throws Exception {
		String sql = "delete from tuserrole where eno='" +employee.getEno()+ "'";
		System.out.println("empno="+employee.getEno());
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;	
		}

	}

	/**
	 * 更新雇员之前，删除雇员的原有的权限
	 * 
	 * @param employee
	 */
	public void deletAuth(Employee employee) throws Exception {
		String sql = "delete from temployauth where eno='" + employee.getEno()
				+ "'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

}
