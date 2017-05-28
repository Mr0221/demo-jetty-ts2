package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import com.icss.dto.LoginUser;
import com.icss.dto.RightDTO;

public class UserDao  extends BaseDao{		
	
	/**
	 * 用户身份的校验：用户登录成功，则提取该用户的所有权限信息
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public LoginUser login(String uname,String pwd) throws Exception{
		String sql;
		LoginUser user = null;		
		
		sql = "select * from TUser where uname =? and pwd=?";				
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
		ResultSet rs = ps.executeQuery();					
		while (rs.next()) {
			user = new LoginUser();
			user.setUname(uname);
			user.setPwd(pwd);					
			user.setEno(rs.getString("eno"));			
		}
		rs.close();
		ps.close();			
		if(user != null){	
			sql = "select u.uname,u.eno,a.authname,a.aid,a.moduleid " +
				  " from tuser u, tuserrole ur, trole r, tauth a, troleauth ra " +
				  " where u.eno = ur.eno  and r.id = ur.rid  and r.id = ra.rno  and a.aid = ra.ano and u.uname=?";
			String sql2 = "select u.uname,u.eno,a.authname,a.aid,a.moduleid  " +
					      " from temployauth ua,tuser u,tauth a where u.eno = ua.eno and a.aid = ua.authno and u.uname = ?";
			sql = sql + " union " + sql2;			
		    ps = this.conn.prepareStatement(sql);
		    ps.setString(1, uname);
		    ps.setString(2, uname);
			rs = ps.executeQuery();					
			Map<String,RightDTO> szRights = new HashMap<String,RightDTO>();
			while (rs.next()) {
				RightDTO right = new RightDTO();
				right.setRightName(rs.getString("authname"));			
				right.setRightID(rs.getString("aid"));			
				right.setModelID(rs.getString("moduleid"));
				szRights.put(right.getRightID(), right);
			}			
			rs.close();
			ps.close();
			user.setAllRights(szRights);        				//给用户赋权限				
		}		
		
		return user;		
	}	
	
}
