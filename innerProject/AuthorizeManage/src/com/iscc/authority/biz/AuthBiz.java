package com.iscc.authority.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.ls.LSInput;

import com.iscc.authority.dao.AuthDao;
import com.iscc.authority.entity.Auth;



public class AuthBiz {
	
	/**
	 * ��ȡ��Ӧѡ���µĽ�ɫ
	 * @return
	 */
	public List<Auth> getAuthList(String moduleId) throws Exception{
		AuthDao authDao = new AuthDao();
		List<Auth> authList = new ArrayList<Auth>();
		try {
			authList = authDao.getSubAuth(moduleId);
		} catch (Exception e) {
			throw e;
		}finally{
			authDao.closeConnection();
		}
		
		return authList;
	}

	
	/**
	 * ��ȡԱ��������Ȩ��
	 * @param emNo
	 * @return
	 */
	public List<Auth> getAuth(String emNo)throws Exception{
		AuthDao authDao = new AuthDao();
		List<Auth> auths;
		try {
			auths = authDao.getAuth(emNo);
		} catch (Exception e) {
			throw e;
		}
		
		return auths;
		
	}
	
	/**
	 * ���ݽ�ɫ��Ų�ѯ���е�Ȩ��
	 * @return
	 */
	public List<Auth> getAuthByRoleNo(int rNo) throws Exception{
		AuthDao authDao = new AuthDao();
		List<Auth> auths;
		try {
			auths = authDao.getAuthByRoleNo(rNo);
		} catch (Exception e) {
			throw e;
		}finally{
			authDao.closeConnection();
		}
		
		return auths;
	}
	
	/**
	 * ��ѯ���е�Ȩ��
	 */
	public List<Auth> getAllAuth() throws Exception{
		List<Auth> authList;
		AuthDao authDao = new AuthDao();
		try {
			authList = authDao.getAllAuth();
		} catch (Exception e) {
			throw e;
		}finally{
			authDao.closeConnection();
		}
		return authList;
	}
	
}
