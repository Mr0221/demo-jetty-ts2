package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Role;



public class AuthDao extends BaseDao{

	/**
	 * ����ģ��id��ѯ�����е�Ȩ��
	 * @param moduleId
	 * @return
	 * @throws Exception
	 */
	public List<Auth> getSubAuth(String moduleId) throws Exception{
		String sql = "select *from tauth where moduleid='"+moduleId+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		List<Auth> authList = new ArrayList<Auth>();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Auth auth = new Auth();
				auth.setaId(rs.getString("aid"));
				auth.setAuthName(rs.getString("authname"));
				auth.setModuleId(rs.getString("moduleid"));
				authList.add(auth);
			}
		} catch (Exception e) {
			throw e;
		}
		return authList;
	}
	
	/**
	 * ������ӵ�еĽ�ɫ����ȡ���е�Ȩ��
	 * @param roleList
	 * @return
	 */
	public List<Auth> getAuth(String emNo)throws Exception{
		String sql = "select a.aid,a.authname from temployauth ea,tauth a where ea.eno='"+emNo+"' and ea.authno=a.aid";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Auth> auths = new ArrayList<Auth>();
		while(rs.next()){
			Auth auth = new Auth();
			auth.setaId(rs.getString("aid"));
			auth.setAuthName(rs.getString("authname"));
			auths.add(auth);
		}
		
		return auths;
	}
	
	/**
	 * ���ݽ�ɫ��Ų�ѯ���е�Ȩ��,ע���ɫ��ͬ����������ͬ��Ȩ�޵Ľ��棬��Ҫȥ����ͬ��Ȩ��
	 * @param rNo
	 * @return
	 */
	public List<Auth> getAuthByRoleNo(int rNo) throws Exception{
		String sql = "select distinct ta.aid,ta.authname from tauth ta,troleauth tra where tra.rno='"+rNo+"' and tra.ano=ta.aid";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		List<Auth> auths =new ArrayList<Auth>();
		try {
			ResultSet rs = ps.executeQuery();	
			while(rs.next()){
				Auth auth = new Auth();
				auth.setaId(rs.getString("aid"));
				auth.setAuthName(rs.getString("authname"));
				auths.add(auth);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return auths;
	}
	
	/**
	 * ��ȡ���е�Ȩ��
	 * @return
	 * @throws Exception
	 */
	public List<Auth> getAllAuth() throws Exception{
		String sql = "select *from tauth";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Auth> authList = new ArrayList<Auth>();
		while(rs.next()){
			Auth auth = new Auth();
			auth.setaId(rs.getString("aid"));
			auth.setAuthName(rs.getString("authname"));
			authList.add(auth);
		}
		return authList;
	}
	
	/**
	 * �ӵ��м����
	 * @param auths
	 */
	public void addRoleAuth(List<Auth> auths)throws Exception{
		this.OpenConncetion();
		for(int i=0;i<auths.size();i++){
			Auth auth = auths.get(i);
			String sql ="insert into troleauth values((select nvl(max(id),0)+1 from troleauth),(select nvl(max(id),0) from trole),'"+auth.getaId()+"')";
			PreparedStatement ps =connection.prepareStatement(sql);
			try {
				ps.executeUpdate();	
			} catch (Exception e) {
				 e.printStackTrace();
			}	
		} 
	}
}
