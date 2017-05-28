package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.ws.soap.MTOMFeature;

import com.iscc.authority.biz.AddBiz;
import com.iscc.authority.biz.AuthBiz;
import com.iscc.authority.biz.DepartmentBiz;
import com.iscc.authority.biz.EmployBiz;
import com.iscc.authority.biz.RoleBiz;
import com.iscc.authority.biz.UserBiz;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Department;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;
import com.iscc.util.Log;

public class AddUserFrame implements ActionListener {

	static Department dept;						// ���մ�������dept
	static JTree tree;							// ������ӳɹ�֮�������
	static DefaultMutableTreeNode selectNode;	// ��ǰѡ�нڵ�
	JDialog dialog;
	JTextField txtEmno;							// Ա�����
	JTextField txtName;							// Ա������
	JTextField txtDep;							// ��������
	JRadioButton rbtnMan;						// ��
	JRadioButton rbtnWoman;						// Ů

	JTextField txtUName;						// �û���
	JPasswordField txtPwd;						// ����
	JPasswordField txtEnPwd;					// ȷ������

	/**************** �����õ�Table������װѡ���Ȩ�� ****************/
	Object[] rowValues;
	String[][] authTableValues = {};
	String[] selectColumnName = { "Ȩ��ID", "Ȩ������" };	
	static JPanel roleAuthPanel;
	static JTable commonTable;

	static JScrollPane selecyRoleAuthPane = new JScrollPane(commonTable);;

	public Map<String, String> authMap;					   //���ѡ���˵�����Ȩ�ޣ���ɫ��Ȩ��+ֱ��ѡ���Ȩ�ޣ�
	public DefaultTableModel   selectRoleAuthModel;
	/******************************************************/

	JButton btn3;// Ӧ��
	JButton btn4;// ȡ��

	JSplitPane split;// װһ����Ϣ��split
	JPanel rolePanel = new JPanel(new GridLayout(3, 0));// װ��ɫ��ǩ��panel

	JTable table1;// ��ɫ������Ϸ��ı��
	JTable table2;// ��ɫ�����·��ı��
	MyTableModel upModel = new MyTableModel();			// ���н�ɫ

	private DefaultTableModel tableModel;// ����ı��ģ��
	private List<Role> selectRole;
	int x = 0;
	private AddAuth addAuth;

	public AddUserFrame() {
		selectRole = new ArrayList<Role>();
		/***************** ѡ���ɫ����Ӧ��Ȩ�޵����� ****************************/
		commonTable = new JTable(tableModel);
		selecyRoleAuthPane = new JScrollPane(commonTable);
		roleAuthPanel = new JPanel();
		authMap = new HashMap<String, String>();

		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = makeUserFrame();			// ����������ʾ��ͬ������
		tabbedPane.addTab("һ����Ϣ", panel1);				// �����panel����һ��fram��
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JComponent panel2 = makeRoleFrame();			// ����������ʾ��ͬ������
		tabbedPane.addTab("��ɫ", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		addAuth = new AddAuth(this);
		JComponent panel3 = addAuth.addAuth();			// ����������ʾ��ͬ������
		tabbedPane.addTab("Ȩ��", panel3);
		JComponent panel4 = getRoleAuth();
		tabbedPane.addTab("���е�Ȩ��", panel4);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JPanel panel0 = new JPanel();
		panel0.setSize(400, 30);
		btn3 = new JButton("Ӧ��");// ����frame���button

		btn4 = new JButton("ȡ��");
		panel0.add(btn3);
		panel0.add(btn4);

		// frame = new JFrame("����û�");
		dialog = new JDialog();
		dialog.setTitle("����û�");
		dialog.setSize(560, 560);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.add(tabbedPane, BorderLayout.CENTER);
		dialog.add(panel0, BorderLayout.SOUTH);
		selectRoleAuthModel = new DefaultTableModel(
								authTableValues, selectColumnName);
	}

	/**
	 * ����û�һ����ϢPanel
	 * 
	 * @return
	 */
	protected JComponent makeUserFrame() {
		// JSplitPane split;
		JPanel panel01 = new JPanel();// �����ǹ�Ա��Ϣ
		JPanel panel02 = new JPanel();// �������û���Ϣ
		JPanel panel0 = new JPanel();// �û���Ϣ��ͷ
		JPanel panel00 = new JPanel();// ��Ա��Ϣ��ͷ

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();

		/********** �Ϸ��ı� ***********/
		JLabel label12 = new JLabel("��Ա��Ϣ��д");
		label12.setForeground(Color.blue);
		label12.setPreferredSize(new Dimension(400, 20));

		JLabel lblEmno = new JLabel("Ա�����:");
		JLabel lblDeptno = new JLabel("��������:");
		JLabel lblEmName = new JLabel("Ա������:");
		JLabel lblSex = new JLabel("�Ա�:");
		txtEmno = new JTextField(25);// Ա�����
		txtDep = new JTextField(25);// ��������
		txtDep.setText(dept.getDeptName());
		txtDep.setEditable(false);

		txtName = new JTextField(25);// Ա������
		rbtnMan = new JRadioButton("��", true);// ��radio
		rbtnWoman = new JRadioButton("Ů");
		ButtonGroup sexRbtn = new ButtonGroup();// �ӵ�buttonGroup��
		sexRbtn.add(rbtnMan);
		sexRbtn.add(rbtnWoman);
		panel00.add(label12);
		panel4.add(lblEmno);
		panel4.add(txtEmno);
		panel5.add(lblDeptno);
		panel5.add(txtDep);
		panel6.add(lblEmName);
		panel6.add(txtName);
		panel7.add(lblSex);
		panel7.add(rbtnMan);
		panel7.add(rbtnWoman);
		panel7.setPreferredSize(new Dimension(250, 40));

		panel01.add(panel00, BorderLayout.CENTER);
		panel01.add(panel4, BorderLayout.CENTER);
		panel01.add(panel5, BorderLayout.CENTER);
		panel01.add(panel6, BorderLayout.CENTER);
		panel01.add(panel7, BorderLayout.CENTER);

		/******** �·��ı� *************/
		JLabel label11 = new JLabel("�û���Ϣ��д");
		label11.setPreferredSize(new Dimension(400, 20));
		label11.setForeground(Color.blue);
		JLabel label1 = new JLabel("��     �� : ");
		JLabel label2 = new JLabel("�������:");
		JLabel label3 = new JLabel("ȷ�Ͽ���:");
		txtUName = new JTextField(25);// �û���
		txtPwd = new JPasswordField(25);
		txtEnPwd = new JPasswordField(25);
		panel0.add(label11);
		panel1.add(label1);
		panel1.add(txtUName);
		panel2.add(label2);
		panel2.add(txtPwd);
		panel3.add(label3);
		panel3.add(txtEnPwd);
		panel02.add(panel0, BorderLayout.CENTER);
		panel02.add(panel1, BorderLayout.CENTER);
		panel02.add(panel2, BorderLayout.CENTER);
		panel02.add(panel3, BorderLayout.CENTER);
		/*******************************************/

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel01, panel02);// ��ֱ�������
		split.setDividerLocation(200);// ���÷ָ�����λ��
		split.setDividerSize(8);// ���÷ָ����Ŀ��

		return split;

	}

	/**
	 * ��ӽ�ɫ��Ϣ �����������������ı���Ǵ����ݿ����ѯ����Щ��ɫ������ı������ʾ��ǰ�û��Ľ�ɫ
	 * 
	 * @return
	 */
	protected JComponent makeRoleFrame() {

		JPanel subPanel2 = new JPanel();

		/*** ����ı�� */
		table1 = new JTable(upModel);// �����ݿ���ȡ�����еĽ�ɫ
		table1.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table1.setFillsViewportHeight(true);
		JScrollPane scrollPane1 = new JScrollPane(table1);

		/****** �м�İ�ť *******/

		JButton btn1 = new JButton("ѡ��");
		btn1.addActionListener(new AddRole(btn1));					// ��Ӽ���

		JButton btn2 = new JButton("�Ƴ�");
		btn2.addActionListener(new AddRole(btn2));
		subPanel2.add(btn1);
		subPanel2.add(btn2);

		/***** ����ı�� ***/
		String[] columnNames = { "���", "��ɫ" };
		String[][] tableValues = {};
		tableModel = new DefaultTableModel(tableValues, columnNames);
		table2 = new JTable(tableModel);
		JScrollPane scrollPane2 = new JScrollPane(table2);

		/************************/

		rolePanel.add(scrollPane1);
		rolePanel.add(subPanel2);
		rolePanel.add(scrollPane2);

		return rolePanel;
	}

	protected JComponent getRoleAuth() {
		return roleAuthPanel;
	}

	/*************************** �ڲ��� ********************************************/
	/**
	 * ��ӽ�ɫ���·��ı��
	 * 
	 * @author Administrator
	 * 
	 */
	class AddRole implements ActionListener {
		private JButton btn;

		public AddRole(JButton btn) {
			this.btn = btn;

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (btn.getText().equals("ѡ��")) {
				int selectRow = table1.getSelectedRow();
				String rNo = "";
				String rName = "";
				if (selectRow != -1) {
					rNo = table1.getValueAt(selectRow, 0).toString();
					rName = table1.getValueAt(selectRow, 1).toString();

					boolean b = false;				// ��ʶ�Ƿ��Ѿ�ѡ���˸ý�ɫ

					if (selectRole.size() == 0) {
						Role role = new Role();						
						role.setId(Integer.parseInt(rNo));
						role.setName(rName);
						
					} else if (selectRole.size() > 0) {
						for (int m = 0; m < selectRole.size(); m++) {
							Role role = selectRole.get(m);
							if (rNo.equals(String.valueOf(role.getId()))) {
								JOptionPane.showMessageDialog(table2,
										"�Ѿ�ѡ���˸ý�ɫ��");
								b = true;
								break;
							}
						}
					}
					if (b == false) {		// ���û��ѡ��ý�ɫ������ӵ�����ı����!Ȼ�����ѡ�񵽵Ľ�ɫ��ѯȨ�޼ӵ�Ȩ�ޱ�ǩ����ѡ�����
						Role role = new Role();
						role.setId(Integer.parseInt(rNo));
						role.setName(rName);
						selectRole.add(role);
						String[] rowValues = { rNo, rName };
						tableModel.addRow(rowValues);

						getAuthByRole(Integer.parseInt(rNo));			// ����ѡ�еĽ�ɫ��ѯȨ��
						
						for (String key : authMap.keySet()) {
							String auName = authMap.get(key);
							String[] rowValue = { key, auName };
							selectRoleAuthModel.addRow(rowValue);
						}

						commonTable.setModel(selectRoleAuthModel);

						selecyRoleAuthPane.getViewport().add(commonTable);
						roleAuthPanel.add(selecyRoleAuthPane);

						selecyRoleAuthPane.validate();						// ˢ��
						roleAuthPanel.validate();
						// }

					}
					dialog.repaint();

				}
			} else if (btn.getText().equals("�Ƴ�")) {					// �Ƴ���ɫ
				int selectRow = table2.getSelectedRow();
				if (selectRow != -1) {
					// ���Ȼ�ȡ�ý�ɫ��ɶȨ��
					String rNo = table2.getValueAt(selectRow, 0).toString();
					tableModel.removeRow(selectRow);
					List<Auth> auths = getAuthByRNo(Integer.parseInt(rNo));

					// ����Ҫ��һ��һ�����Ƴ�modoel�е�����
					for (int i = 0; i < authMap.size(); i++) {
						selectRoleAuthModel.removeRow(0);
					}

					// ���Ƴ�map�е�����
					for (Auth a : auths) {
						authMap.remove(a.getaId().toString());// �Ƴ���authMap�е�
					}

					// ���²�ѯһ�����ٷŽ�ȥ�������Ҫ����~~~�޸��㿴����
					for (String key : authMap.keySet()) {
						String auName = authMap.get(key);
						String[] rowValue = { key, auName };
						selectRoleAuthModel.addRow(rowValue);
					}
					commonTable.setModel(selectRoleAuthModel);
					selecyRoleAuthPane.getViewport().add(commonTable);
					roleAuthPanel.add(selecyRoleAuthPane);
					selecyRoleAuthPane.validate();// ˢ��
					roleAuthPanel.validate();

					/*
					 * selectRole[x][0] = ""; selectRole[x][0] = ""; x--;
					 */
					// �Ƴ���ɫʱ
					selectRole.remove(selectRow);
					dialog.repaint();
				}
			}
		}

		// ���ݽ�ɫ��Ų�ѯ�ý�ɫ��ӵ�е�Ȩ��
		public Map<String, String> getAuthByRole(int rNo) {
			List<Auth> authList;
			AuthBiz authBiz = new AuthBiz();
			try {
				authList = authBiz.getAuthByRoleNo(rNo);
				for (Auth auth : authList) {
					//if (authMap.get(auth.getaId()) == null) {
						authMap.put(auth.getaId(), auth.getAuthName());
					//}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
			}
			return authMap;
		}

		// ����Ҫ�Ƴ��Ľ�ɫ���Ƴ��ܵ�Ȩ�ޱ������ݡ��������������ӵ������/(��o��)/~~ ~~~ ~~
		public List<Auth> getAuthByRNo(int rNo) {
			List<Auth> authList = null;
			AuthBiz authBiz = new AuthBiz();
			try {
				authList = authBiz.getAuthByRoleNo(rNo);
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
			}
			return authList;
		}
	}//AddRole class end

	/**
	 * Ӧ�õ�ʱ�򣬻�ȡ���е���Ϣ������ӣ��������������� 
	 * 1.��ȡ��Ա��Ϣ 2.��ȡ�û���Ϣ 3.��ȡ��ɫ��Ϣ 4.��ȡȨ����Ϣ
	 * �ֱ���ӵ����ݱ��е�˳��Ϊ�� 1.��Ա�� 2.�û��� 3.�û���ɫ�� 4.�û�Ȩ��
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AddBiz addBiz = new AddBiz();
		Employee employee = new Employee();
		User user = new User();

		/********** ��Ա��Ϣ ********/
		String eNo = txtEmno.getText().trim();		// Ա�����
		String deptNo = dept.getDeptNo();			// ���ű��
		String eName = txtName.getText().trim();	// Ա������
		String sex = "";							// �Ա�
		if (rbtnMan.isSelected()) {
			sex = rbtnMan.getText();
		} else if (rbtnWoman.isSelected()) {
			sex = rbtnWoman.getText();
		}

		if (eNo.equals("") || eName.equals("")) {
			JOptionPane.showMessageDialog(dialog, "��Ա��Ż��Ա������Ϊ��");
			return;
		} else {
			employee.setEno(eNo);
			employee.setEname(eName);
			employee.setDeptNo(deptNo);
			employee.setSex(sex);
		}

		/******************* �û���Ϣ *******************/
		String uname = txtUName.getText().trim();				// �û���
		String pwd = String.valueOf(txtPwd.getPassword());		// ���룬ע������toString()���У���������룬ֻ��ͨ��valueOf��ʽ
		String enPwd = String.valueOf(txtEnPwd.getPassword());	// ȷ������

		if (uname.equals("") || pwd.equals("") || enPwd.equals("")) {
			JOptionPane.showMessageDialog(dialog, "�û��������벻��Ϊ��");
			return;
		} else if (!pwd.equals(enPwd)) {
			JOptionPane.showMessageDialog(dialog, "�������벻һ��");
			return;
		} else {
			user.setEno(eNo);
			user.setUname(uname);
			user.setPwd(pwd);
		}

		/********************* ѡ�н�ɫ *********************/
		if (this.authMap.size() > 0 ) {
			try {
				addBiz.add(employee, user, selectRole, addAuth.selectAuth); 
				JOptionPane.showMessageDialog(dialog, "��ӳɹ�");
				selectNode.removeAllChildren();// ������
				new DepartmentBiz().getSubDept(selectNode);
				tree.updateUI();
				dialog.dispose();
			} catch (Exception e1) {
				Log.logger.error(e1.getMessage());
				JOptionPane.showMessageDialog(dialog, "���ʧ�ܣ�����");
			}
		}else{
			JOptionPane.showMessageDialog(dialog, "�������û���Ȩ��");
		}
	}

	/**
	 * ��ʾ���Ա���ĶԻ���
	 */
	public void show() {
		dialog.setVisible(true);
		btn3.addActionListener(this);
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
	}
}

/**
 * ������񣬴����ݿ���ȡ�����еĽ�ɫ
 * 
 * @author Administrator
 * 
 */
class MyTableModel extends AbstractTableModel {
	private String[] columnNames = { "���", "��ɫ" };
	private List<Role> roleList = new ArrayList<Role>();
	private Object[][] data;

	public MyTableModel() {
		getRole();

	}

	public Object[][] getData() {
		return data;
	}

	/**
	 * �����ݿ���ȡ�����еĽ�ɫ
	 */
	public void getRole() {
		RoleBiz roleBiz = new RoleBiz();
		try {
			roleList = roleBiz.getRole();
			data = new Object[roleList.size()][2];
			for (int i = 0; i < roleList.size(); i++) {      
				Role role = roleList.get(i);
				data[i][0] = role.getId();
				data[i][1] = role.getName();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
