package com.icss.dto;
/**
 * 用户的权限对象
 */
public class RightDTO {
	private String rightID;              //权限ID
	private String rightName;            //权限名称
	private String modelID;              //所属模块ID	
	
	public String getRightID() {
		return rightID;
	}
	public void setRightID(String rightID) {
		this.rightID = rightID;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getModelID() {
		return modelID;
	}
	public void setModelID(String modelID) {
		this.modelID = modelID;
	}	
}
