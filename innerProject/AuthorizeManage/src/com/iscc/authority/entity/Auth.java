package com.iscc.authority.entity;

public class Auth {
	private String aId;				//Ȩ�ޱ��
	private String authName;		//Ȩ����
	private String moduleId;		//����ģ����
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
