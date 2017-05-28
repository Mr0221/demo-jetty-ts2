package com.iscc.authority.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.dao.AuthDao;
import com.iscc.authority.dao.RoleDao;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Role;

public class RoleBiz {
	
	/**
	 * ��ȡ���еĽ�ɫ������ӵ��ܽ����
	 * @param node
	 * @throws Exception 
	 */
	public void getAllRole(DefaultMutableTreeNode node) throws Exception{
		List<Role> roleList = new ArrayList<Role>();
		RoleDao roleDao = new RoleDao();
		try {
			if(node.toString().equals("���н�ɫ")){		
				roleList = roleDao.getAllRole();
				for(Role r:roleList){
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(r);
					node.add(childNode);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			roleDao.closeConnection();
		}
	}

	/**
	 * ��ȡ���еĽ�ɫ
	 */
	public List<Role> getRole() throws Exception{
		List<Role> roleList = new ArrayList<Role>();
		RoleDao roleDao = new RoleDao();
		try {	
				roleList = roleDao.getAllRole();
		} catch (Exception e) {
			throw e;
		}finally{
			roleDao.closeConnection();
		}
		return roleList;
	}
	
	/**
	 * �����û����
	 * @param roleNo
	 * @return
	 */
	public List<Role> getRole(String empNo) throws Exception{
		RoleDao roleDao = new RoleDao();
		List<Role> roleList;
		try {
			roleList = roleDao.getRole(empNo);
		} catch (Exception e) {
			throw e;
		}finally{
			roleDao.closeConnection();
		}
	
		return roleList;
	}
	
	/**
	 * ��ӽ�ɫ
	 * @param rName
	 * @param authList
	 */
	public void addRoleAndAuth(String rName,List<Auth> authList)throws Exception{
		RoleDao roleDao = new RoleDao();
		AuthDao authDao = new AuthDao();
		authDao.setConnection(roleDao.getConnection());
		try {
			roleDao.beginTransaction();
			roleDao.addRole(rName);//�����ɫ����
			authDao.addRoleAuth(authList);//�����м��
			roleDao.commit();
		} catch (Exception e) {
			roleDao.rollback();
		}finally{
			roleDao.closeConnection();
		}
	}
	

}
