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
	private JLabel label0;// ͼƬ��
	private JLabel label1;// �û�����
	private JTextField txtfield;// �û�����
	private JLabel label02;// ���룺
	private JLabel label2;// ���룺
	private JPasswordField pwdfield;// �����
	private JButton button1;// ȷ�ϰ�ť
	private JButton button2;// �رհ�ť

	public LoginFrame() {
		frame = new JFrame("�û���¼");
		frame.setResizable(false);
		frame.setLayout(new GridLayout(3, 1));// ���м��к��ݼ��
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		ImageIcon icon=new ImageIcon("./images/�û�.png");//�û���ͼ��
		label0=new JLabel(icon);
		label1 = new JLabel("�û�����");
		txtfield = new JTextField(12);
		ImageIcon icon1=new ImageIcon("./images/����.png");//����ͼ��
		label02=new JLabel(icon1);
		label2 = new JLabel("��   �룺");
		pwdfield = new JPasswordField(12);
		button1 = new JButton("ȷ��");
		button2 = new JButton("�ر�");

		panel1.add(label0);
		panel1.add(label1);
		panel1.add(txtfield);
		panel2.add(label02);
		panel2.add(label2);
		panel2.add(pwdfield);
		panel3.add(button1);
		panel3.add(button2);
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));//��ť����
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
				frame.dispose();//���ڹر�
				
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		
		String uname=txtfield.getText().trim();//��ȡ�û���
		String pwd=new String(pwdfield.getPassword()).trim();//��ȡ���룬ע�������ַ���
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
				Log.logger.info("�û������������");
				JOptionPane.showMessageDialog(frame, "�û�����������������µ�¼");
				txtfield.setText("");
				pwdfield.setText("");
			}
			
		}
	
	}

}
