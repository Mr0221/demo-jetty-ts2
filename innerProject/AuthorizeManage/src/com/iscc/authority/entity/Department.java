package com.iscc.authority.entity;

public class Department {
	
	private String deptNo;//���ű��
	private String deptName;//������
	private String parentDept;//�����ű��
	private Department parentDepart;
	private String deptDesc;//���ż��
	
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
	public String toString() {//�ص���toString(),�ڵ����ʾ�ı�����toString
		return this.getDeptName();
	}
	
		
	
	

}
