package com.iscc.authority.entity;

public class Department {
	
	private String deptNo;//部门编号
	private String deptName;//部门名
	private String parentDept;//父部门编号
	private Department parentDepart;
	private String deptDesc;//部门简介
	
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public Department getParentDepart() {
		return parentDepart;
	}
	public void setParentDepart(Department parentDepart) {
		this.parentDepart = parentDepart;
	}
	/*public Department(String deptName){
		this.deptName=deptName;
		
	}*/
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParentDept() {
		return parentDept;
	}
	public void setParentDept(String parentDept) {
		this.parentDept = parentDept;
	}
	
	
	@Override
	public String toString() {//重点在toString(),节点的显示文本就是toString
		return this.getDeptName();
	}
	
		
	
	

}
