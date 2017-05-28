package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iscc.authority.entity.Role;

public class RoleDao extends BaseDao{

	/**
	 * 查询所有的角色
	 * @return
	 * @throws Exception
	 */
	public List<Role> getAllRole() throws Exception{
		String sql = "select * from trole";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<Role> roleList = new ArrayList<Role>();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				roleList.add(role);
			}
		} catch (Exception e) {
			throw e;
		}
		return roleList;
	}
	
	/**
	 * 根据员工编号查询员工所有的角色
	 * @param empNo
	 * @return
	 */
	public List<Role> getRole(String empNo) throws Exception{
		String sql = "select r.id,r.name from trole r,tuserrole ur where ur.eno='"+empNo+"' and ur.rid=r.id";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Role> roles = new ArrayList<Role>();
		while(rs.next()){
			Role role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			roles.add(role);	
		}
		
		return roles;
	}
	
	
	/**
	 * 添加角色
	 */
	public void addRole(String rName)throws Exception{
		String sql = "insert into trole values((select nvl(max(id),0)+1 from trole),'"+rName+"')";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
	}
}
