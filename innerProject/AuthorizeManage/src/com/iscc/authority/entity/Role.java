package com.iscc.authority.entity;

public class Role {

	private int id;//��ɫ���
	private String name;//��ɫ��
	public Role(){
		this.name="��ɫ";//��ʼ��
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
