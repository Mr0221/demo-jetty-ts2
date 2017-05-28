package com.icss.dto;

import java.util.Map;

public class LoginUser {
	private String uname;
	private String pwd;	
	private String eno;                              //员工编号
	private Map<String,RightDTO> allRights;          //当前登录用户的所有权限信息
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public Map<String, RightDTO> getAllRights() {
		return allRights;
	}
	public void setAllRights(Map<String, RightDTO> allRights) {
		this.allRights = allRights;
	}
}
