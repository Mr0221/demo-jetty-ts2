package com.iscc.authority.entity;

public class Module {

	private String mId;//Ä£¿éid
	private String mname;
	private String parentModuleNo;
	private Module parentModule;//¸¸Ä£¿é
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	public String getParentModuleNo() {
		return parentModuleNo;
	}
	public void setParentModuleNo(String parentModuleNo) {
		this.parentModuleNo = parentModuleNo;
	}
	
	@Override
	public String toString() {
		
		return this.getMname();
	}
	
}
