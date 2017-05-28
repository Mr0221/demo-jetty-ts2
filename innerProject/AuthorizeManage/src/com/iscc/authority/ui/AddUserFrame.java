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

	static Department dept;						// 接收传过来的dept
	static JTree tree;							// 用于添加成功之后更新树
	static DefaultMutableTreeNode selectNode;	// 当前选中节点
	JDialog dialog;
	JTextField txtEmno;							// 员工编号
	JTextField txtName;							// 员工姓名
	JTextField txtDep;							// 所属部门
	JRadioButton rbtnMan;						// 男
	JRadioButton rbtnWoman;						// 女

	JTextField txtUName;						// 用户名
	JPasswordField txtPwd;						// 密码
	JPasswordField txtEnPwd;					// 确认密码

	/**************** 公共用的Table，用于装选择的权限 ****************/
	Object[] rowValues;
	String[][] authTableValues = {};
	String[] selectColumnName = { "权限ID", "权限名称" };	
	static JPanel roleAuthPanel;
	static JTable commonTable;

	static JScrollPane selecyRoleAuthPane = new JScrollPane(commonTable);;

	public Map<String, String> authMap;					   //存放选择了的所有权限（角色的权限+直接选择的权限）
	public DefaultTableModel   selectRoleAuthModel;
	/******************************************************/

	JButton btn3;// 应用
	JButton btn4;// 取消

	JSplitPane split;// 装一般信息的split
	JPanel rolePanel = new JPanel(new GridLayout(3, 0));// 装角色标签的panel

	JTable table1;// 角色里面的上方的表格
	JTable table2;// 角色里面下方的表格
	MyTableModel upModel = new MyTableModel();			// 所有角色

	private DefaultTableModel tableModel;// 下面的表格模型
	private List<Role> selectRole;
	int x = 0;
	private AddAuth addAuth;

	public AddUserFrame() {
		selectRole = new ArrayList<Role>();
		/***************** 选择角色表格对应的权限的内容 ****************************/
		commonTable = new JTable(tableModel);
		selecyRoleAuthPane = new JScrollPane(commonTable);
		roleAuthPanel = new JPanel();
		authMap = new HashMap<String, String>();

		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = makeUserFrame();			// 往容器里显示不同的内容
		tabbedPane.addTab("一般信息", panel1);				// 这里把panel换成一个fram表单
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JComponent panel2 = makeRoleFrame();			// 往容器里显示不同的内容
		tabbedPane.addTab("角色", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		addAuth = new AddAuth(this);
		JComponent panel3 = addAuth.addAuth();			// 往容器里显示不同的内容
		tabbedPane.addTab("权限", panel3);
		JComponent panel4 = getRoleAuth();
		tabbedPane.addTab("所有的权限", panel4);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JPanel panel0 = new JPanel();
		panel0.setSize(400, 30);
		btn3 = new JButton("应用");// 整个frame里的button

		btn4 = new JButton("取消");
		panel0.add(btn3);
		panel0.add(btn4);

		// frame = new JFrame("添加用户");
		dialog = new JDialog();
		dialog.setTitle("添加用户");
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
	 * 添加用户一般信息Panel
	 * 
	 * @return
	 */
	protected JComponent makeUserFrame() {
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
		JLabel label12 = new JLabel("雇员信息填写");
		label12.setForeground(Color.blue);
		label12.setPreferredSize(new Dimension(400, 20));

		JLabel lblEmno = new JLabel("员工编号:");
		JLabel lblDeptno = new JLabel("所属部门:");
		JLabel lblEmName = new JLabel("员工姓名:");
		JLabel lblSex = new JLabel("性别:");
		txtEmno = new JTextField(25);// 员工编号
		txtDep = new JTextField(25);// 所属部门
		txtDep.setText(dept.getDeptName());
		txtDep.setEditable(false);

		txtName = new JTextField(25);// 员工姓名
		rbtnMan = new JRadioButton("男", true);// 男radio
		rbtnWoman = new JRadioButton("女");
		ButtonGroup sexRbtn = new ButtonGroup();// 加到buttonGroup中
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

		/******** 下方的表单 *************/
		JLabel label11 = new JLabel("用户信息填写");
		label11.setPreferredSize(new Dimension(400, 20));
		label11.setForeground(Color.blue);
		JLabel label1 = new JLabel("名     称 : ");
		JLabel label2 = new JLabel("输入口令:");
		JLabel label3 = new JLabel("确认口令:");
		txtUName = new JTextField(25);// 用户名
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

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel01, panel02);// 垂直方向分裂
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

		/*** 上面的表格 */
		table1 = new JTable(upModel);// 从数据库中取出所有的角色
		table1.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table1.setFillsViewportHeight(true);
		JScrollPane scrollPane1 = new JScrollPane(table1);

		/****** 中间的按钮 *******/

		JButton btn1 = new JButton("选择");
		btn1.addActionListener(new AddRole(btn1));					// 添加监听

		JButton btn2 = new JButton("移除");
		btn2.addActionListener(new AddRole(btn2));
		subPanel2.add(btn1);
		subPanel2.add(btn2);

		/***** 下面的表格 ***/
		String[] columnNames = { "序号", "角色" };
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

		@Override
		public void actionPerformed(ActionEvent e) {

			if (btn.getText().equals("选择")) {
				int selectRow = table1.getSelectedRow();
				String rNo = "";
				String rName = "";
				if (selectRow != -1) {
					rNo = table1.getValueAt(selectRow, 0).toString();
					rName = table1.getValueAt(selectRow, 1).toString();

					boolean b = false;				// 标识是否已经选择了该角色

					if (selectRole.size() == 0) {
						Role role = new Role();						
						role.setId(Integer.parseInt(rNo));
						role.setName(rName);
						
					} else if (selectRole.size() > 0) {
						for (int m = 0; m < selectRole.size(); m++) {
							Role role = selectRole.get(m);
							if (rNo.equals(String.valueOf(role.getId()))) {
								JOptionPane.showMessageDialog(table2,
										"已经选择了该角色！");
								b = true;
								break;
							}
						}
					}
					if (b == false) {		// 如果没有选择该角色，则添加到下面的表格中!然后根据选择到的角色查询权限加到权限标签的已选择表中
						Role role = new Role();
						role.setId(Integer.parseInt(rNo));
						role.setName(rName);
						selectRole.add(role);
						String[] rowValues = { rNo, rName };
						tableModel.addRow(rowValues);

						getAuthByRole(Integer.parseInt(rNo));			// 根据选中的角色查询权限
						
						for (String key : authMap.keySet()) {
							String auName = authMap.get(key);
							String[] rowValue = { key, auName };
							selectRoleAuthModel.addRow(rowValue);
						}

						commonTable.setModel(selectRoleAuthModel);

						selecyRoleAuthPane.getViewport().add(commonTable);
						roleAuthPanel.add(selecyRoleAuthPane);

						selecyRoleAuthPane.validate();						// 刷新
						roleAuthPanel.validate();
						// }

					}
					dialog.repaint();

				}
			} else if (btn.getText().equals("移除")) {					// 移除角色
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
						authMap.remove(a.getaId().toString());// 移除掉authMap中的
					}

					// 重新查询一遍表格，再放进去，我真的要哭了~~~哭给你看！哼
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

					/*
					 * selectRole[x][0] = ""; selectRole[x][0] = ""; x--;
					 */
					// 移除角色时
					selectRole.remove(selectRow);
					dialog.repaint();
				}
			}
		}

		// 根据角色编号查询该角色所拥有的权限
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

		// 根据要移除的角色，移除总的权限表格的内容——————复杂的我想哭/(ㄒoㄒ)/~~ ~~~ ~~
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
	 * 应用的时候，获取所有的信息进行添加！！！！！！！！ 
	 * 1.获取雇员信息 2.获取用户信息 3.获取角色信息 4.获取权限信息
	 * 分别添加到数据表中的顺序为： 1.雇员表 2.用户表 3.用户角色表 4.用户权限
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AddBiz addBiz = new AddBiz();
		Employee employee = new Employee();
		User user = new User();

		/********** 雇员信息 ********/
		String eNo = txtEmno.getText().trim();		// 员工编号
		String deptNo = dept.getDeptNo();			// 部门编号
		String eName = txtName.getText().trim();	// 员工姓名
		String sex = "";							// 性别
		if (rbtnMan.isSelected()) {
			sex = rbtnMan.getText();
		} else if (rbtnWoman.isSelected()) {
			sex = rbtnWoman.getText();
		}

		if (eNo.equals("") || eName.equals("")) {
			JOptionPane.showMessageDialog(dialog, "雇员编号或雇员名不能为空");
			return;
		} else {
			employee.setEno(eNo);
			employee.setEname(eName);
			employee.setDeptNo(deptNo);
			employee.setSex(sex);
		}

		/******************* 用户信息 *******************/
		String uname = txtUName.getText().trim();				// 用户名
		String pwd = String.valueOf(txtPwd.getPassword());		// 密码，注意这里toString()不行，会出现乱码，只有通过valueOf方式
		String enPwd = String.valueOf(txtEnPwd.getPassword());	// 确认密码

		if (uname.equals("") || pwd.equals("") || enPwd.equals("")) {
			JOptionPane.showMessageDialog(dialog, "用户名或密码不能为空");
			return;
		} else if (!pwd.equals(enPwd)) {
			JOptionPane.showMessageDialog(dialog, "两次密码不一致");
			return;
		} else {
			user.setEno(eNo);
			user.setUname(uname);
			user.setPwd(pwd);
		}

		/********************* 选中角色 *********************/
		if (this.authMap.size() > 0 ) {
			try {
				addBiz.add(employee, user, selectRole, addAuth.selectAuth); 
				JOptionPane.showMessageDialog(dialog, "添加成功");
				selectNode.removeAllChildren();// 更新树
				new DepartmentBiz().getSubDept(selectNode);
				tree.updateUI();
				dialog.dispose();
			} catch (Exception e1) {
				Log.logger.error(e1.getMessage());
				JOptionPane.showMessageDialog(dialog, "添加失败，请检查");
			}
		}else{
			JOptionPane.showMessageDialog(dialog, "请设置用户的权限");
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
}

/**
 * 创建表格，从数据库中取出所有的角色
 * 
 * @author Administrator
 * 
 */
class MyTableModel extends AbstractTableModel {
	private String[] columnNames = { "序号", "角色" };
	private List<Role> roleList = new ArrayList<Role>();
	private Object[][] data;

	public MyTableModel() {
		getRole();

	}

	public Object[][] getData() {
		return data;
	}

	/**
	 * 从数据库中取出所有的角色
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
