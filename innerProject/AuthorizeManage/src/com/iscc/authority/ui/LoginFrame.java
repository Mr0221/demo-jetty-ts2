package com.iscc.authority.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iscc.authority.biz.LoginBiz;
import com.iscc.util.Log;

public class LoginFrame implements ActionListener {

	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel label0;// 图片：
	private JLabel label1;// 用户名：
	private JTextField txtfield;// 用户名框
	private JLabel label02;// 密码：
	private JLabel label2;// 密码：
	private JPasswordField pwdfield;// 密码框
	private JButton button1;// 确认按钮
	private JButton button2;// 关闭按钮

	public LoginFrame() {
		frame = new JFrame("用户登录");
		frame.setResizable(false);
		frame.setLayout(new GridLayout(3, 1));// 几行几列横纵间距
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		ImageIcon icon=new ImageIcon("./images/用户.png");//用户名图标
		label0=new JLabel(icon);
		label1 = new JLabel("用户名：");
		txtfield = new JTextField(12);
		ImageIcon icon1=new ImageIcon("./images/密码.png");//密码图标
		label02=new JLabel(icon1);
		label2 = new JLabel("密   码：");
		pwdfield = new JPasswordField(12);
		button1 = new JButton("确认");
		button2 = new JButton("关闭");

		panel1.add(label0);
		panel1.add(label1);
		panel1.add(txtfield);
		panel2.add(label02);
		panel2.add(label2);
		panel2.add(pwdfield);
		panel3.add(button1);
		panel3.add(button2);
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));//按钮靠右
		panel3.setLocation(100, 50);
		
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.setSize(240, 160);
		frame.setLocation(500, 300);
		frame.setBackground(Color.blue);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		button1.addActionListener(this);
		button2.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//窗口关闭
				
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		
		String uname=txtfield.getText().trim();//获取用户名
		String pwd=new String(pwdfield.getPassword()).trim();//获取密码，注意用这种方法
		if(uname!=null && pwd!=null &&uname!=""&& pwd != ""){
			LoginBiz biz=new LoginBiz();
			boolean b=false;
			try {
				b = biz.login(uname, pwd);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(b){
				new LeftTree();
				frame.setVisible(false);
			}else {
				Log.logger.info("用户名或密码错误");
				JOptionPane.showMessageDialog(frame, "用户名或密码错误，请重新登录");
				txtfield.setText("");
				pwdfield.setText("");
			}
			
		}
	
	}

}
