package com.iscc.authority.entity;

public class Role {

	private int id;//角色编号
	private String name;//角色名
	public Role(){
		this.name="角色";//初始化
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
}
