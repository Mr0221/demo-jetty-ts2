package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;


public class ConnectDataBase implements ActionListener,MouseListener{

	JPanel dataConfig;
	JPanel leftPanel;
	JLabel leftLable;
	JSplitPane split;
	Dialog dialog;
	JLabel lblDataType;//数据库类型：
	JComboBox comboBox;
	JLabel lblService;//服务器
	JTextField txtService;//服务器名
	JLabel lblDataName;//数据库名
	JTextField txtDataName;
	JLabel lblUname;//用户名
	JTextField txtUserName;//用户名
	JLabel lblPwd;
	JPasswordField txtPwd;//密码
	JButton btn1;
	JButton btn2;
	JPanel panel0;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	
	public JTextField getTxtService() {
		return txtService;
	}

	public void setTxtService(JTextField txtService) {
		this.txtService = txtService;
	}

	public JTextField getTxtDataName() {
		return txtDataName;
	}

	public void setTxtDataName(JTextField txtDataName) {
		this.txtDataName = txtDataName;
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JPasswordField getTxtPwd() {
		return txtPwd;
	}

	public void setTxtPwd(JPasswordField txtPwd) {
		this.txtPwd = txtPwd;
	}

	public ConnectDataBase(){
		ImageIcon icon=new ImageIcon("./images/数据库.png");//用户名图标
		leftLable=new JLabel(icon);
		leftPanel = new JPanel();
		dataConfig = new JPanel();
		dataConfig.setBorder(BorderFactory.createTitledBorder("配置信息"));
		dataConfig.setForeground(Color.RED);
		dataConfig.setPreferredSize(new Dimension(400, 250));
		lblDataType = new JLabel("数据库类型：");
		comboBox = new JComboBox();
		comboBox.addItem("MySql");
		comboBox.addItem("Oracle");
		comboBox.addItem("MySql");
		comboBox.setPreferredSize(new Dimension(160,20));
		panel0 = new JPanel();
		panel0.add(lblDataType);
		panel0.add(comboBox);
		
		lblService = new JLabel("服务器名：");
		txtService = new JTextField(15);
		txtService.setText("localhost");
		panel1 = new JPanel();
		panel1.add(lblService);
		panel1.add(txtService);
		
		lblDataName = new JLabel("数据库名：");
		txtDataName = new JTextField(15);
		txtDataName.setText("jiangli");
		panel2 = new JPanel();
		panel2.add(lblDataName);
		panel2.add(txtDataName);
		
		lblUname = new JLabel("用  户  名：");
		txtUserName = new JTextField(15);
		txtUserName.setText("aa");
		panel3 = new JPanel();
		panel3.add(lblUname);
		panel3.add(txtUserName);
		
		lblPwd = new JLabel(" 密      码： ");
		txtPwd = new JPasswordField(15);
		txtPwd.setText("123456");
		panel4 = new JPanel();
		panel4.add(lblPwd);
		panel4.add(txtPwd);
		
		btn1 = new JButton("确定");
		btn2 = new JButton("取消");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				
			}
		});
		panel5 = new JPanel();
		panel5.add(btn1);
		panel5.add(btn2);
		panel5.setPreferredSize(new Dimension(150,50));
		
		dataConfig.add(panel0);
		dataConfig.add(panel1);
		dataConfig.add(panel2);
		dataConfig.add(panel3);
		dataConfig.add(panel4);
		dataConfig.add(panel5);
		leftPanel.add(leftLable);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, dataConfig);// 垂直方向分裂
		split.setDividerSize(8);// 设置分隔条的宽度

		split.setDividerLocation(150);
		
		
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		dialog = new JDialog();
		dialog.setTitle("连接数据库");
		dialog.setSize(560, 300);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.add(split);
		//dialog.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//new ConnectDataBase();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
