package com.iscc.authority.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.biz.DepartmentBiz;
import com.iscc.authority.entity.Department;
import com.iscc.util.Log;

public class UpdateDept implements ActionListener{
	static Department department;//获取传过来的员工
	static JTree tree;//获取传过来的树
	static DefaultMutableTreeNode selectNode;
	DefaultMutableTreeNode top = new DefaultMutableTreeNode("对象权限管理");// 总节点
	private JDialog frame;

	private JLabel label0;
	private JLabel lable1;// 部门名称
	private JLabel lable2;// 部门描述

	private JLabel warLabel0;// 提示label
	private JLabel warLabel1;

	private JTextField deptNo;// 部门编号
	private JTextField deptName;// 部门名称框
	private JTextArea deptDesc;// 部门描述

	private JButton button1;// 确认
	private JButton button2;// 取消

	
	public UpdateDept(){
		frame=new JDialog();
		frame.setTitle("部门信息");
		frame.setLayout(null);// 几行几列横纵间距
		label0 = new JLabel("部门编号:");
		label0.setBounds(110, 50, 70,25);
		frame.add(label0);
		deptNo = new JTextField(20);
		deptNo.setBounds(180, 50, 250,25);
		deptNo.setText(department.getDeptNo());
		deptNo.setEditable(false);
		frame.add(deptNo);
		warLabel0 = new JLabel("*编号不可更改");
		warLabel0.setForeground(Color.red);
		warLabel0.setBounds(450, 50, 90, 25);
		frame.add(warLabel0);
		lable1=new JLabel("部门名称:");
		lable1.setBounds(110, 100, 70,25);
		frame.add(lable1);
		deptName=new JTextField(20);
		deptName.setBounds(180,100,250,25);
		deptName.setText(department.getDeptName());
		frame.add(deptName);
		warLabel1 = new JLabel("*唯一不为空");
		warLabel1.setForeground(Color.red);
		warLabel1.setBounds(450, 100, 90, 25);
		frame.add(warLabel1);
		lable2=new JLabel("部门描述:");
		lable2.setBounds(110,150,70,25);
		frame.add(lable2);
		deptDesc=new JTextArea(10, 30);
		deptDesc.setBounds(180,150,250,160);
		deptDesc.setText(department.getDeptDesc());
		frame.add(deptDesc);
		button1=new JButton("修改");
		button1.setBounds(300,350,60,30);
		frame.add(button1);
		button2=new JButton("取消");
		button2.setBounds(370,350,60,30);
		frame.add(button2);
		
		frame.setSize(560,450);
		frame.setResizable(false);//固定frame的大小
		frame.setLocationRelativeTo(null);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Department dept = new Department();
		String deNo = deptNo.getText().trim();//部门编号
		String deName = deptName.getText().trim();//部门名
		String deDesc = deptDesc.getText().trim();//部门简介
		DepartmentBiz deptBiz = new DepartmentBiz();
		dept.setDeptNo(deNo);
		dept.setDeptName(deName);
		dept.setDeptDesc(deDesc);
		try {
			deptBiz.updateDept(dept);
			JOptionPane.showMessageDialog(frame, "修改成功");
			selectNode.removeAllChildren();//更新树
			new DepartmentBiz().getSubDept(selectNode);
			tree.updateUI();
			frame.dispose();
		} catch (Exception e2) {
			e2.printStackTrace();
			Log.logger.error(e2.getMessage());
		}	
	}
	
	/**
	 * 显示此对话框，因为要先把参数传过来，再显示对话框
	 */
	public void show(){
		frame.setVisible(true);
		button1.addActionListener(this);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
