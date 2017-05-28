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
	private JLabel lable1;//��������
	private JLabel lable2;//��������
	
	private JLabel warLabel0;//��ʾlabel
	private JLabel warLabel1;
	
	private JTextField deptNo;//���ű��
	private JTextField deptName;// �������ƿ�
	private JTextArea deptDesc;//��������
	
	private JButton button1;//ȷ��
	private JButton button2;//ȡ��
	
	static Department department;//��ȡ�������Ľڵ�
	
	static DefaultMutableTreeNode selectNode;//��ȡ��������ѡ��node
	
	static JTree tree;//��ȡ����������
	

	public AddDepartFrame(){
		frame=new JDialog();
		frame.setTitle("������Ϣ");
		frame.setLayout(null);// ���м��к��ݼ��
		label0 = new JLabel("���ű��:");
		label0.setBounds(110, 50, 70,25);
		frame.add(label0);
		deptNo = new JTextField(20);
		deptNo.setText(department.getDeptNo()+"-");
		deptNo.setBounds(180, 50, 250,25);
		frame.add(deptNo);
		warLabel0 = new JLabel("*��Ű�������ʽ��0*");
		warLabel0.setForeground(Color.red);
		warLabel0.setBounds(450, 50, 150, 25);
		frame.add(warLabel0);
		lable1=new JLabel("��������:");
		lable1.setBounds(110, 100, 70,25);
		frame.add(lable1);
		deptName=new JTextField(20);
		deptName.setBounds(180,100,250,25);
		frame.add(deptName);
		warLabel1 = new JLabel("*Ψһ��Ϊ��");
		warLabel1.setForeground(Color.red);
		warLabel1.setBounds(450, 100, 90, 25);
		frame.add(warLabel1);
		lable2=new JLabel("��������:");
		lable2.setBounds(110,150,70,25);
		frame.add(lable2);
		deptDesc=new JTextArea(10, 30);
		deptDesc.setBounds(180,150,250,160);
		frame.add(deptDesc);
		button1=new JButton("ȷ��");
		button1.setBounds(300,350,60,30);
		frame.add(button1);
		button2=new JButton("ȡ��");
		button2.setBounds(370,350,60,30);
		frame.add(button2);
		
		frame.setSize(600,460);
		frame.setResizable(false);//�̶�frame�Ĵ�С
		frame.setLocationRelativeTo(null);
	}

	/**
	 * ȷ�������Ӳ���
	 */
	public void actionPerformed(ActionEvent e) {
		String deNo = deptNo.getText().trim();//���ű��
		String deName = deptName.getText().trim();//������
		String deDesc = deptDesc.getText().trim();//���ż��
		DepartmentBiz deptBiz = new DepartmentBiz();
		try {
			deptBiz.addDepartment(deNo, deName, department.getDeptNo(),deDesc);
			JOptionPane.showMessageDialog(frame, "���ӳɹ�");
			selectNode.removeAllChildren();//������
			new DepartmentBiz().getSubDept(selectNode);
			tree.updateUI();
			frame.dispose();
		} catch (Exception e1) {
			e1.printStackTrace();
			Log.logger.error(e1.getMessage());
			JOptionPane.showMessageDialog(frame, "����ʧ��");
		}
		
	}
	
	/**
	 * ��ʾ�˶Ի�����ΪҪ�ȰѲ���������������ʾ�Ի���
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