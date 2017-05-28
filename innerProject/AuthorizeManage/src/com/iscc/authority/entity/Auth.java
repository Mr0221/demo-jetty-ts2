package com.iscc.authority.entity;

public class Auth {
	private String aId;				//权限编号
	private String authName;		//权限名
	private String moduleId;		//功能模块编号
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	@Override
	public String toString() {
		
		return this.getAuthName();
	}

}
