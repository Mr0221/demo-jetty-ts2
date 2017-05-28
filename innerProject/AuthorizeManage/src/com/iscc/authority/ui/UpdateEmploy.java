package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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

public class UpdateEmploy implements ActionListener {
	UserBiz userBiz = new UserBiz();
	User user = new User();
	static DefaultMutableTreeNode node;
	static Employee selectEmp;// 选中的用户结点
	static Department selectDept;
	static JTree tree;
	DefaultMutableTreeNode top = new DefaultMutableTreeNode("模块权限管理");// 总节点
	
	JDialog dialog;
	JTextField txtEmno;// 员工编号
	JTextField txtName;// 员工姓名
	JTextField txtDep;// 所属部门
	JRadioButton rbtnMan;// 男
	JRadioButton rbtnWoman;// 女

	JTextField txtUName;// 用户名
	JPasswordField txtPwd;// 密码
	JPasswordField txtEnPwd;// 确认密码

	/**************** 公共用的Table，用于装选择的权限 ****************/
	Object[] rowValues;
	String[][] authTableValues = {};
	String[] selectColumnName = { "权限ID", "权限名称" };
	static DefaultTableModel selectRoleAuthModel;
	static JPanel roleAuthPanel;
	static JTable commonTable;// = new JTable(selectRoleAuthModel);

	static JScrollPane selecyRoleAuthPane;
	static public Map<String, String> authMap;// 存放选择了的权限
	JButton btn3;// 应用
	JButton btn4;// 取消

	JSplitPane split;// 装一般信息的split
	JPanel rolePanel = new JPanel(new GridLayout(3, 0));// 装角色标签的panel

	JTable table1;// 角色里面的上方的表格
	JTable table2;// 角色里面下方的表格
	UpTableModel upModel = new UpTableModel();// 上方的表格模型	

	DefaultTableModel tableModel;// 角色的下面的表格模型
	JScrollPane scrollPane2;// 角色下面的表格scroll
	List<Role> roleList;// 存放从数据库取出的用户的角色
	Object[] roleRowValue;// 存放下方的表格的角色

	static List<Role> selectRoles;
	static int x = 0;
	Employee employee;
	User user2;
	String sex;
	private UpdateAddAuth updateAuth;

	public UpdateEmploy() {
		selectRoles = new ArrayList<Role>();
		table2 = new JTable();
		scrollPane2 = new JScrollPane(table2);
		employee = new Employee();
		user2 = new User();
		/***************** 选择角色表格对应的权限的内容 ****************************/
		selectRoleAuthModel = new DefaultTableModel(authTableValues,
				selectColumnName);
		commonTable = new JTable(selectRoleAuthModel);
		selecyRoleAuthPane = new JScrollPane(commonTable);
		roleAuthPanel = new JPanel();
		authMap = new HashMap<String, String>();

		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = makeUserFrame();// 往容器里显示不同的内容
		tabbedPane.addTab("一般信息", panel1);// 这里把panel换成一个fram表单
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JComponent panel2 = makeRoleFrame();// 往容器里显示不同的内容
		tabbedPane.addTab("角色", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		updateAuth = new UpdateAddAuth();
		JComponent panel3 = updateAuth.addAuth();// 往容器里显示不同的内容
		tabbedPane.addTab("权限", panel3);
		JComponent panel4 = getRoleAuth();
		tabbedPane.addTab("所有的权限", panel4);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JPanel panel0 = new JPanel();
		panel0.setSize(400, 30);
		btn3 = new JButton("应用");// 整个frame里的button
		btn4 = new JButton("取消");
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();

			}
		});
		panel0.add(btn3);
		panel0.add(btn4);

		dialog = new JDialog();
		dialog.setTitle("编辑用户");
		dialog.setSize(560, 560);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.add(tabbedPane, BorderLayout.CENTER);
		dialog.add(panel0, BorderLayout.SOUTH);

	}

	/**
	 * 添加用户一般信息Panel
	 * 
	 * @return
	 */
	protected JComponent makeUserFrame() {
		boolean b = false, c = false;
		// JSplitPane split;
		JPanel panel01 = new JPanel();// 上面是雇员信息
		JPanel panel02 = new JPanel();// 下面是用户信息
		JPanel panel0 = new JPanel();// 用户信息表头
		JPanel panel00 = new JPanel();// 雇员信息表头

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();

		/********** 上方的表单 ***********/
		JLabel label12 = new JLabel("雇员信息");
		label12.setForeground(Color.blue);
		label12.setPreferredSize(new Dimension(400, 20));

		JLabel lblEmno = new JLabel("员工编号:");
		JLabel lblDeptno = new JLabel("所属部门:");
		JLabel lblEmName = new JLabel("员工姓名:");
		JLabel lblSex = new JLabel("性别:");
		txtEmno = new JTextField(23);// 员工编号
		txtEmno.setText(selectEmp.getEno());
		txtEmno.setEditable(false);
		txtDep = new JTextField(23);// 所属部门
		txtDep.setPreferredSize(new Dimension(240, 20));// !!从数据库中取出所有的部门
		txtDep.setText(selectEmp.getDeptNo());
		txtDep.setEditable(false);

		txtName = new JTextField(23);// 员工姓名
		txtName.setText(selectEmp.getEname());
		if (selectEmp.getSex().equals("男")) {
			b = true;
		} else {
			c = true;
		}
		rbtnMan = new JRadioButton("男", b);// 男radio
		rbtnWoman = new JRadioButton("女", c);
		if (rbtnMan.isSelected()) {
			sex = "男";
		} else {
			sex = "女";
		}
		ButtonGroup sexRbtn = new ButtonGroup();// 加到buttonGroup中
		sexRbtn.add(rbtnMan);
		sexRbtn.add(rbtnWoman);
		panel00.add(label12);
		panel4.add(lblEmno);
		panel4.add(txtEmno);
		panel5.add(lblDeptno);
		panel5.add(txtDep);
		panel5.setPreferredSize(new Dimension(330, 30));
		panel6.add(lblEmName);
		panel6.add(txtName);
		panel7.add(lblSex);
		panel7.add(rbtnMan);
		panel7.add(rbtnWoman);
		panel7.setPreferredSize(new Dimension(250, 40));
		// lblSex.setAlignmentX(5);

		panel01.add(panel00, BorderLayout.CENTER);
		panel01.add(panel4, BorderLayout.CENTER);
		panel01.add(panel5, BorderLayout.CENTER);
		panel01.add(panel6, BorderLayout.CENTER);
		panel01.add(panel7, BorderLayout.CENTER);

		/******** 下方的表单 *************/
		JLabel label11 = new JLabel("用户信息填写");
		label11.setPreferredSize(new Dimension(400, 20));
		label11.setForeground(Color.blue);
		JLabel label1 = new JLabel("名     称 : ");
		JLabel label2 = new JLabel("输入口令:");
		JLabel label3 = new JLabel("确认口令:");
		txtUName = new JTextField(25);// 用户名

		try {
			// 根据雇员编号查询用户信息
			user = userBiz.getUser(selectEmp.getEno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtUName.setText(user.getUname());
		txtPwd = new JPasswordField(25);
		txtPwd.setText(user.getPwd());
		txtEnPwd = new JPasswordField(25);
		txtEnPwd.setText(user.getPwd());
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

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel01, panel02);// 水平方向分裂
		split.setDividerLocation(200);// 设置分割条的位置
		split.setDividerSize(8);// 设置分隔条的宽度

		return split;

	}

	/**
	 * 添加角色信息 有上下两个表格，上面的表格是从数据库里查询有哪些角色，下面的表格是显示当前用户的角色
	 * 
	 * @return
	 */
	protected JComponent makeRoleFrame() {

		JPanel subPanel2 = new JPanel();
		AddRole addRole = new AddRole();
		/*** 上面的表格 */
		table1 = new JTable(upModel);// 从数据库中取出所有的角色
		table1.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table1.setFillsViewportHeight(true);
		JScrollPane scrollPane1 = new JScrollPane(table1);

		/****** 中间的按钮 *******/

		JButton btn1 = new JButton("选择");
		btn1.addActionListener(new AddRole(btn1));// 添加监听

		JButton btn2 = new JButton("移除");
		btn2.addActionListener(new AddRole(btn2));
		subPanel2.add(btn1);
		subPanel2.add(btn2);

		/***** 下面的表格 ***/
		String[] columnNames = { "序号", "角色" };
		String[][] tableValues = {};

		tableModel = new DefaultTableModel(tableValues, columnNames);
		// 第二种！！！！
		RoleBiz roleBiz = new RoleBiz();
		try {
			roleList = roleBiz.getRole(selectEmp.getEno());
			for (int k = 0; k < roleList.size(); k++) {// roleLis的size行，2列
				Role role = roleList.get(k);
				String rId = String.valueOf(role.getId());
				String roleName = role.getName();
				roleRowValue = new Object[] { rId, roleName };
				tableModel.addRow(roleRowValue);
				// 同时往已经添加的角色数组中添加
				UpdateEmploy.selectRoles.add(role);
				addRole.getAuthByRole(role.getId());// 根据选中的角色查询该角色拥有的权限

			}
			if (authMap.size()>0) {
				for (String key : authMap.keySet()) {
					String auName = authMap.get(key);
					String[] rowValue = { key, auName };
					selectRoleAuthModel.addRow(rowValue);
				}
			}

			table2.setModel(tableModel);
			table2.updateUI();
			commonTable.setModel(selectRoleAuthModel);
			selecyRoleAuthPane.getViewport().add(commonTable);
			roleAuthPanel.add(selecyRoleAuthPane);
			selecyRoleAuthPane.validate();// 刷新
			roleAuthPanel.validate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/************************/

		rolePanel.add(scrollPane1);
		rolePanel.add(subPanel2);
		rolePanel.add(scrollPane2);

		return rolePanel;
	}

	protected JComponent getRoleAuth() {
		return roleAuthPanel;
	}

	/*************************** 内部类 ********************************************/
	/**
	 * 添加角色到下方的表格
	 * 
	 * @author Administrator
	 * 
	 */
	class AddRole implements ActionListener {
		private JButton btn;

		public AddRole(JButton btn) {
			this.btn = btn;

		}

		public AddRole() {
			this.btn = null;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (btn.getText().equals("选择") || btn == null) {
				int selectRow = table1.getSelectedRow();
				String rNo = "";
				String rName = "";
				if (selectRow != -1) {

					rNo = table1.getValueAt(selectRow, 0).toString();
					rName = table1.getValueAt(selectRow, 1).toString();

					boolean b = false;// 标识是否已经选择了该角色
					if (selectRoles.size() == 0) {
						Role role = new Role();
						String[] rowValues = { rNo, rName };
						tableModel.addRow(rowValues);
						role.setId(Integer.parseInt(rNo));
						role.setName(rName);
						selectRoles.add(role);
					} else if (selectRoles.size() > 0) {
						for (int m = 0; m < selectRoles.size(); m++) {
							Role role = selectRoles.get(m);
							if (rNo.equals(String.valueOf(role.getId()))) {
								JOptionPane.showMessageDialog(table1,"已经选择了该角色！");
								b = true;
								break;
							}
						}
						if (b == false) {// 如果没有选择该角色，则添加到下面的表格中!然后根据选择到的角色查询权限加到权限标签的已选择表中
							Role role = new Role();
							role.setId(Integer.parseInt(rNo));
							role.setName(rName);
							selectRoles.add(role);
							String[] rowValues = { rNo, rName };
							tableModel.addRow(rowValues);

							getAuthByRole(Integer.parseInt(rNo));// 根据选中的角色查询该角色拥有的权限
							if (authMap != null) {
								selectRoleAuthModel = new DefaultTableModel(authTableValues, selectColumnName);
								for (String key : authMap.keySet()) {
									String auName = authMap.get(key);
									String[] rowValue = { key, auName };
									selectRoleAuthModel.addRow(rowValue);
								}

								commonTable.setModel(selectRoleAuthModel);
								selecyRoleAuthPane.getViewport().add(commonTable);
								roleAuthPanel.add(selecyRoleAuthPane);

								selecyRoleAuthPane.validate();// 刷新
								roleAuthPanel.validate();
							}
						}
					}
					dialog.repaint();

				}
			} else if (btn.getText().equals("移除")) {// 移除角色
				int selectRow = table2.getSelectedRow();
				if (selectRow != -1) {
					// 首先获取该角色有啥权限
					String rNo = table2.getValueAt(selectRow, 0).toString();
					tableModel.removeRow(selectRow);
					List<Auth> auths = getAuthByRNo(Integer.parseInt(rNo));

					// 必须要先一个一个的移除modoel中的数据
					for (int i = 0; i < authMap.size(); i++) {
						selectRoleAuthModel.removeRow(0);
					}

					// 再移除map中的数据
					for (Auth a : auths) {
						authMap.remove(String.valueOf(a.getaId()));// 移除掉authMap中的
					}

					// 重新查询一遍表格，再放进去，我真的要哭了~~~哭给你看！哼
					for (String key : authMap.keySet()) {
						String auName = authMap.get(key);
						String[] rowValue = { key, auName };
						selectRoleAuthModel.addRow(rowValue);
					}
					selectRoles.remove(selectRow);
					// 移除角色时

					commonTable.setModel(selectRoleAuthModel);
					selecyRoleAuthPane.getViewport().add(commonTable);
					roleAuthPanel.add(selecyRoleAuthPane);
					selecyRoleAuthPane.validate();// 刷新
					roleAuthPanel.validate();	
					
					dialog.repaint();
				}
			}
		}

		// 根据角色编号查询所拥有的权限
		public Map<String, String> getAuthByRole(int rNo) {
			List<Auth> authList;
			AuthBiz authBiz = new AuthBiz();
			try {
				authList = authBiz.getAuthByRoleNo(rNo);
				for (Auth auth : authList) {
					if (authMap.get(auth.getaId()) == null) {
						authMap.put(auth.getaId(), auth.getAuthName());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
			}
			return authMap;
		}

		// 根据要移除的角色，移除总的权限表格的内容——————复杂的我想哭/(ㄒoㄒ)/~~
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
	}

	/**
	 * 显示添加员工的对话框
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

	/**
	 * 点击应用，更新员工信息到数据库中 1.更新雇员表 2.更新角色，先删除该用户拥有的角色，再加入SelectRole中的角色
	 * 3.删除该用户拥有的权限，再加入该用户所有的权限authmap
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		user.setUname(txtUName.getText().trim());
		user.setPwd(String.valueOf(txtPwd.getPassword()));
		employee.setEno(txtEmno.getText().trim());
		employee.setSex(sex);
		employee.setEname(txtName.getText().trim());
		EmployBiz employBiz = new EmployBiz();
		try {
			employBiz.updateEmploy(employee, user, selectRoles, updateAuth.selectAuth);
			JOptionPane.showMessageDialog(dialog, "更新成功");
			node.removeAllChildren();//更新树,传过来父节点
			new DepartmentBiz().getSubDept(node);
			tree.updateUI();
		
			dialog.dispose();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}

/**
 * 创建表格，从数据库中取出所有的角色
 * 
 * @author Administrator
 * 
 */
class UpTableModel extends AbstractTableModel {
	private String[] columnNames = { "序号", "角色" };
	private List<Role> roleList = new ArrayList<Role>();
	private Object[][] data;

	public UpTableModel() {
		getRole();

	}

	public Object[][] getData() {
		return data;
	}

	/**
	 * 从数据库中取出所有的角色，同时，根据角色，取出所有的权限
	 */
	public void getRole() {
		RoleBiz roleBiz = new RoleBiz();
		try {
			roleList = roleBiz.getRole();
			data = new Object[roleList.size()][2];
			for (int i = 0; i < roleList.size(); i++) {// roleLis的size行，2列
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