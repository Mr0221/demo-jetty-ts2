package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.biz.DepartmentBiz;
import com.iscc.authority.entity.Department;
import com.iscc.util.Log;

public class AddDepartFrame implements ActionListener{

	private JDialog frame;
	
	private JLabel label0;
	private JLabel lable1;//部门名称
	private JLabel lable2;//部门描述
	
	private JLabel warLabel0;//提示label
	private JLabel warLabel1;
	
	private JTextField deptNo;//部门编号
	private JTextField deptName;// 部门名称框
	private JTextArea deptDesc;//部门描述
	
	private JButton button1;//确认
	private JButton button2;//取消
	
	static Department department;//获取传过来的节点
	
	static DefaultMutableTreeNode selectNode;//获取传过来的选中node
	
	static JTree tree;//获取传过来的树
	

	public AddDepartFrame(){
		frame=new JDialog();
		frame.setTitle("部门信息");
		frame.setLayout(null);// 几行几列横纵间距
		label0 = new JLabel("部门编号:");
		label0.setBounds(110, 50, 70,25);
		frame.add(label0);
		deptNo = new JTextField(20);
		deptNo.setText(department.getDeptNo()+"-");
		deptNo.setBounds(180, 50, 250,25);
		frame.add(deptNo);
		warLabel0 = new JLabel("*编号按给定格式加0*");
		warLabel0.setForeground(Color.red);
		warLabel0.setBounds(450, 50, 150, 25);
		frame.add(warLabel0);
		lable1=new JLabel("部门名称:");
		lable1.setBounds(110, 100, 70,25);
		frame.add(lable1);
		deptName=new JTextField(20);
		deptName.setBounds(180,100,250,25);
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
		frame.add(deptDesc);
		button1=new JButton("确认");
		button1.setBounds(300,350,60,30);
		frame.add(button1);
		button2=new JButton("取消");
		button2.setBounds(370,350,60,30);
		frame.add(button2);
		
		frame.setSize(600,460);
		frame.setResizable(false);//固定frame的大小
		frame.setLocationRelativeTo(null);
	}

	/**
	 * 确认添加子部门
	 */
	public void actionPerformed(ActionEvent e) {
		String deNo = deptNo.getText().trim();//部门编号
		String deName = deptName.getText().trim();//部门名
		String deDesc = deptDesc.getText().trim();//部门简介
		DepartmentBiz deptBiz = new DepartmentBiz();
		try {
			deptBiz.addDepartment(deNo, deName, department.getDeptNo(),deDesc);
			JOptionPane.showMessageDialog(frame, "添加成功");
			selectNode.removeAllChildren();//更新树
			new DepartmentBiz().getSubDept(selectNode);
			tree.updateUI();
			frame.dispose();
		} catch (Exception e1) {
			e1.printStackTrace();
			Log.logger.error(e1.getMessage());
			JOptionPane.showMessageDialog(frame, "添加失败");
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
