package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.PopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.iscc.authority.biz.AuthBiz;
import com.iscc.authority.biz.DepartmentBiz;
import com.iscc.authority.biz.EmployBiz;
import com.iscc.authority.biz.RoleBiz;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Department;
import com.iscc.authority.entity.Employee;
import com.iscc.authority.entity.Role;
import com.iscc.authority.entity.User;
import com.iscc.authority.my.MyTree;
import com.iscc.util.Log;

public class LeftTree extends JFrame implements ActionListener,
		TreeSelectionListener {

	static  JScrollPane treePane;
	private JMenuBar jmb;
	private JMenu file, util, newfile;
	private JMenuItem user, seluser, dep, people, role, database;
	private JLabel label;
	// 节点
	public JTree tree;
	private JSplitPane split;
	// private JTextArea area;

	/******* 表格的控件 *********/
	DefaultTableModel tableModel;

	private JScrollPane rightPane;// 右侧的pane,存放表格

	private JPanel panel;
	private JPopupMenu popMenu;// 右键菜单
	private JTable table;// 右侧的表格

	DefaultTableCellRenderer tcr;// 表格居中
	String[] columnNames = null;
	Object[] rowValues;
	String[][] tableValues = {};

	static DefaultMutableTreeNode role1;
	public JPopupMenu getPopMenu() {
		return popMenu;
	}

	public void setPopMenu(JPopupMenu popMenu) {
		this.popMenu = popMenu;
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getRightPane() {
		return rightPane;
	}

	public LeftTree() {
		treePane = new JScrollPane();
		
		table = new JTable();
		label = new JLabel();
		panel = new JPanel();
		/*********** 菜单栏 **********/
		jmb = new JMenuBar();

		file = new JMenu("文件");
		util = new JMenu("工具");
		newfile = new JMenu("新建");

		user = new JMenuItem("用户");
		seluser = new JMenuItem("用户查询");
		dep = new JMenuItem("部门");
		people = new JMenuItem("人员");
		role = new JMenuItem("角色");
		database = new JMenuItem("连接数据库");
		
		jmb.add(file);
		jmb.add(util);

		file.add(user);
		file.add(newfile);
		file.add(seluser);

		util.add(database);
		newfile.add(dep);
		newfile.add(people);
		newfile.add(role);

		// 添加菜单
		this.add(jmb, BorderLayout.NORTH);

		/********* 节点 ************/
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("对象权限管理");// 总节点

		this.tree = new JTree(top);
		treePane.getViewport().add(tree);
		rightPane = new JScrollPane(table);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, rightPane);// 垂直方向分裂
		split.setDividerSize(8);// 设置分隔条的宽度

		split.setDividerLocation(150);
		this.setTitle("权限管理");
		this.getContentPane().add(split);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//设对话框为最大
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 表格内容居中
		tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		database.addActionListener(new ConnectDataBase());
		createNodes(top);// 创建节点

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				createPopupMenu();
				showDeptTable();// 显示表格
			}
		});

		/******* 创建右键菜单,并增加监听 ******/
		tree.addMouseListener(new TreePopMenuEvent(this));// 这里!!!!!为树增加监听
		tree.updateUI();

	}

	/**
	 * 创建节点
	 * 
	 * @param top
	 */
	public void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode department = null;// 部门父节点
		DefaultMutableTreeNode subRole = null;// 角色子节点
		DepartmentBiz deptBiz = new DepartmentBiz();
		RoleBiz roleBiz = new RoleBiz();
		try {
			department = new DefaultMutableTreeNode(deptBiz.obtainRootDepart());// 获取所有子部门的总根，"部门"

			deptBiz.getSubDept(department);// 获取子结点

			top.add(department);// 总节点top下的第一个子节点——部门
            Role role = new Role();
            role.setId(0);
            role.setName("所有角色");
			role1 = new DefaultMutableTreeNode(role);
			roleBiz.getAllRole(role1);					// 获取所有的角色加到角色下

			top.add(role1);								// 总节点下的第二个子节点
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}

	}

	/**
	 * 创建右键的菜单
	 */
	public void createPopupMenu() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();// 获取所选中的节点

		Object object;

		if (selectedNode == null|| selectedNode.getUserObject().equals("对象权限管理")) {
			return;
		} else if (/* object != null && */!selectedNode.isRoot()) {
			object = selectedNode.getUserObject();
			String nodeType = "";
			boolean isChild = false;
			if (object instanceof Department) {						// 如果选中的结点是部门类型
				Department dept = (Department) object;
				nodeType = "dept";// 结点类型为部门
				if (dept.getParentDept() != null) {
					isChild = true;// 是子结点
				}
			} else if (object instanceof Role) {					// 如果选中的结点是角色类型
				nodeType = "role";
			} else if (object instanceof Employee) {// 如果选中的结点是员工类型
				nodeType = "employee";
			}

			if (nodeType.equals("dept") && isChild == false) {// 如果选择的是部门结点，只有创建子部门一个菜单
				popMenu = new JPopupMenu();
				JMenuItem addDept = new JMenuItem("添加子部门");
				addDept.addActionListener(new TreeAddDeptMenuEvent(this));     // 添加子部门的监听
				popMenu.add(addDept);

			} else if (nodeType.equals("role")) {    // 如果选择的是角色结点
				Role role = (Role)object;
				if(role.getName().equals("所有角色")){
					popMenu = new JPopupMenu();
					JMenuItem menu = new JMenuItem("创建角色");
					popMenu.add(menu);
					menu.addActionListener(new TreeAddRoleMenuEvent(this));// 添加创建角色的监听	
				}else{
					popMenu = new JPopupMenu();
					JMenuItem menu = new JMenuItem("编辑");//编辑角色
					JMenuItem menu2 = new JMenuItem("删除");//删除角色
					popMenu.add(menu);
					menu.addActionListener(new TreeUpdateRole(this));// 添加创建角色的监听	
				}
			} else if (nodeType.equals("dept") && isChild == true) {// 为部门下的子部门时，创建
				popMenu = new JPopupMenu();
				JMenuItem addDept = new JMenuItem("添加子部门");
				JMenuItem addUser = new JMenuItem("添加员工");
				JMenuItem updateDept = new JMenuItem("编辑");
				JMenuItem delDept = new JMenuItem("删除");
				popMenu.add(addDept);
				addDept.addActionListener(new TreeAddDeptMenuEvent(this));// 添加子部门的监听
				popMenu.add(addUser);
				addUser.addActionListener(new TreeAddUserMenuEvent(this));// 添加用户的监听
				popMenu.add(updateDept);
				updateDept.addActionListener(new TreeUpdateDept(this));
				popMenu.add(delDept);
				delDept.addActionListener(new TreeDelDept(this));// 删除部门
			} else if (nodeType.equals("employee")) {
				popMenu = new JPopupMenu();
				JMenuItem updateEmp = new JMenuItem("编辑");
				updateEmp.addActionListener(new TreeUpdateEmploy(this));
				JMenuItem delEmp = new JMenuItem("删除");
				popMenu.add(updateEmp);
				popMenu.add(delEmp);
				delEmp.addActionListener(new TreeDelDept(this));
			}
		} else if (selectedNode.isRoot()) {
			return;
		}
		// rightPane.removeAll();//清空右侧的所有东西
	}

	public void actionPerformed(ActionEvent e) {

		new AddDepartFrame();
	}

	public void valueChanged(TreeSelectionEvent e) {

	}

	/**
	 * 点击部门时，显示部门和它的子部门列表
	 */
	public void showDeptTable() {
		DepartmentBiz deptBiz = new DepartmentBiz();
		DefaultMutableTreeNode selecNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (selecNode != null) {
			Object object = selecNode.getUserObject();
			if (object instanceof Department) {// 部门节点
				Department selectedDept = (Department) object;
				String[] aa = { "部门编号", "部门名称", "部门简介" };
				columnNames = aa;
				try {
					List<Department> listDept = deptBiz.getAllSubDept(selectedDept.getDeptNo());     //根据部门编号查找出该部门下的子部门List
					tableModel = new DefaultTableModel(tableValues, columnNames);
					for(Department dept : listDept){				    	
						rowValues = new Object[] { dept.getDeptNo(),
								dept.getDeptName(), dept.getDeptDesc() };
						tableModel.addRow(rowValues);	
				    }				    
					table.setModel(tableModel);					
				    rightPane.getViewport().add(table);
				} catch (Exception e) {
					e.printStackTrace();
					Log.logger.error(e.getMessage());
				}

			} else if (object instanceof Employee) {// 雇员节点
				Employee employee = (Employee) object;
				new showUserTable(employee);
			}

			rightPane.repaint();// 用validate不好使
		}
	}

	/********************** 显示用户信息的内部类 ************************/
	/**
	 * 点击左侧的树，右侧通过表格显示信息
	 * 
	 * @author Administrator
	 * 
	 */
	class showUserTable {
		private Employee emp;

		public showUserTable(Employee employee) {
			this.emp = employee;
			getUserMessage();
		}

		public void getUserMessage() {
			JTabbedPane tabbedPane = new JTabbedPane();
			JComponent panel1 = getUser();// 往容器里显示不同的内容
			tabbedPane.addTab("一般信息", panel1);// 这里把panel换成一个fram表单
			panel1.getComponents();
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			JComponent panel2 = getRole();// 往容器里显示不同的内容
			tabbedPane.addTab("角色", panel2);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			JComponent panel3 = getAuth();// 往容器里显示不同的内容
			tabbedPane.addTab("权限", panel3);
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

			rightPane.getViewport().add(tabbedPane);
		}

		/**
		 * 获取一般信息
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getUser() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "员工编号", "员工姓名", "所在部门", "性别" };
			columnNames = aa;
			tableModel = new DefaultTableModel(tableValues, aa);
			table.setDefaultRenderer(Object.class, tcr);
			rowValues = new Object[] { emp.getEno(), emp.getEname(),
					emp.getDeptNo(), emp.getSex() };
			tableModel.addRow(rowValues);
			table.setModel(tableModel);
			scrollPane.getViewport().add(table);
			return scrollPane;
		}

		/**
		 * 获取角色信息
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getRole() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "角色编号", "角色名" };
			columnNames = aa;
			tableModel = new DefaultTableModel(tableValues, aa);
			table.setDefaultRenderer(Object.class, tcr);
			RoleBiz roleBiz = new RoleBiz();
			try {
				List<Role> roles = roleBiz.getRole(emp.getEno());// 根据用户编号查询所拥有的角色
				if (roles != null) {
					for (int i = 0; i < roles.size(); i++) {
						Role role = roles.get(i);
						int roleId = role.getId();
						String roleName = role.getName();
						rowValues = new Object[] { roleId, roleName };
						tableModel.addRow(rowValues);
					}
					table.setModel(tableModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			scrollPane.getViewport().add(table);
			return scrollPane;
		}

		/**
		 * 获取权限信息
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getAuth() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "权限编号", "权限名" };
			columnNames = aa;
			tableModel = new DefaultTableModel(tableValues, aa);

			table.setDefaultRenderer(Object.class, tcr);

			AuthBiz authBiz = new AuthBiz();
			try {
				List<Auth> auths = authBiz.getAuth(emp.getEno());
				if (auths != null) {
					for (int j = 0; j < auths.size(); j++) {
						Auth auth = auths.get(j);
						String authId = auth.getaId();
						String authName = auth.getAuthName();
						rowValues = new Object[] { authId, authName };
						tableModel.addRow(rowValues);
					}
					table.setModel(tableModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			scrollPane.getViewport().add(table);
			return scrollPane;
		}
	}

}

/**
 * 为树添加监听的类
 * 
 * @author Administrator
 * 
 */
class TreePopMenuEvent implements MouseListener {

	private LeftTree adaptee;
	private JTree tree;

	public TreePopMenuEvent(LeftTree adaptee) {
		this.adaptee = adaptee;
		tree = adaptee.getTree();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {// 释放鼠标的时候

		TreePath path = adaptee.getTree()
				.getPathForLocation(e.getX(), e.getY());// 关键是这个方法的使用
		if (path == null) {
			return;
		} else {
			adaptee.getTree().setSelectionPath(path);
			if (e.getButton() == 3) {
				if (adaptee.getPopMenu() != null) {
					adaptee.getPopMenu().show(adaptee.getTree(), e.getX(),
							e.getY());
					adaptee.setPopMenu(null);
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}

/**
 * 右键添加子部门时
 * 
 * @author Administrator
 * 
 */
class TreeAddDeptMenuEvent implements ActionListener {

	private LeftTree leftTree;

	public TreeAddDeptMenuEvent(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		AddDepartFrame deptFrame = null;// 添加子部门对话框
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// 获取选择的结点

		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Department) {
				Department dept = (Department) object;
				AddDepartFrame.department = dept;
				AddDepartFrame.selectNode = selectNode;
				AddDepartFrame.tree = leftTree.getTree();
				deptFrame = new AddDepartFrame();
			}
			deptFrame.show();
		}
	}

}

/**
 * 右键添加员工
 * 
 * @author Administrator
 * 
 */
class TreeAddUserMenuEvent implements ActionListener {
	private LeftTree leftTree;

	public TreeAddUserMenuEvent(LeftTree leftTree) {
		this.leftTree = leftTree;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// 获取选择的结点

		AddUserFrame addUserFrame = null;
		if (selectNode == null) {
		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Department) {
				Department department = (Department) object;
				AddUserFrame.dept = department;// 把当前选中的部门传过去
				AddUserFrame.tree = leftTree.getTree();// 把当前树传过去，便于更新
				AddUserFrame.selectNode = selectNode;// 把当前的结点传过去，便于重新查找
				addUserFrame = new AddUserFrame();// 编辑部门对话框
			}
			addUserFrame.show();
		}
	}

}

/**
 * 右键添加角色
 * 
 * @author Administrator
 * 
 */
class TreeAddRoleMenuEvent implements ActionListener {
	private LeftTree leftTree;

	public TreeAddRoleMenuEvent(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	public void actionPerformed(ActionEvent e) {

		AddRole.node = leftTree.role1;
		AddRole.tree = leftTree.getTree();
		new AddRole();
	}
}

/**
 * 编辑员工
 * 
 * @author Administrator
 * 
 */
class TreeUpdateEmploy implements ActionListener {
	private LeftTree leftTree;

	public TreeUpdateEmploy(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UpdateEmploy updateEmploy = null;
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree.getTree()).getLastSelectedPathComponent();// 获取选择的结点
		DefaultMutableTreeNode selectParentNode = (DefaultMutableTreeNode) selectNode.getParent();
		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			Object parentObject = selectParentNode.getUserObject();
			if (object instanceof Employee) {
				Employee employee = (Employee) object;
				UpdateEmploy.selectEmp = employee;
				if(parentObject instanceof Department){
					Department department = (Department)parentObject;
					UpdateEmploy.node = selectParentNode;
					UpdateEmploy.selectDept = department;
					UpdateEmploy.tree = leftTree.getTree();
				}
				updateEmploy = new UpdateEmploy();
			}
			
		}
		updateEmploy.show();

	}
}

/**
 * 编辑部门
 */
class TreeUpdateDept implements ActionListener {

	private LeftTree leftTree;

	public TreeUpdateDept(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	public void actionPerformed(ActionEvent e) {
		UpdateDept deptFrame = null;
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// 获取选择的结点

		DefaultMutableTreeNode selectParentNode = (DefaultMutableTreeNode) selectNode.getParent();
		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Department) {
				Department department = (Department) object;
				UpdateDept.department = department;
				UpdateDept.tree = leftTree.getTree();
				UpdateDept.selectNode = selectParentNode;
				deptFrame = new UpdateDept();// 编辑部门对话框

			}
		}
		deptFrame.show();
	}

}

/**
 * 删除部门
 * 
 * @author Administrator
 * +
 */
class TreeDelDept implements ActionListener {

	private LeftTree leftTree;

	public TreeDelDept(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// 获取选择的结点
		DefaultTreeModel model = (DefaultTreeModel) leftTree.getTree()
				.getModel(); // 获取树模型

		Object object = selectNode.getUserObject();
		Department dept = new Department();
		Employee emp = new Employee();
		DepartmentBiz deptBiz = new DepartmentBiz();
		EmployBiz empBiz = new EmployBiz();
		if (object instanceof Department) {
			dept = (Department) object;
			try {
				deptBiz.delDept(dept);
				model.removeNodeFromParent(selectNode);
				JOptionPane.showMessageDialog(leftTree.getTree(), "删除成功");
				leftTree.getRightPane().repaint();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(leftTree.getTree(), "请先删除子记录");
				Log.logger.error("先删除子记录，再删除父记录");
			}
		}
		if (object instanceof Employee) {// 删除雇员
			emp = (Employee) object;
			try {
				empBiz.delEmp(emp);
				model.removeNodeFromParent(selectNode);
				JOptionPane.showMessageDialog(leftTree.getTree(), "删除成功");
				leftTree.getTable().validate();
				leftTree.getRightPane().validate();
			} catch (Exception e2) {
				Log.logger.error(e2.getMessage());
			}
		}
	}

}
/**
 * 编辑角色
 * @author Administrator
 *
 */
class TreeUpdateRole implements ActionListener{

	private LeftTree leftTree;

	public TreeUpdateRole(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UpdateRole updateRole = null;
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// 获取选择的结点

		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Role) {
				Role role = (Role) object;
				UpdateRole.role = role;
				UpdateRole.tree = leftTree.getTree();
				UpdateRole.node = selectNode;
				updateRole = new UpdateRole();// 编辑部门对话框

			}
		}
		updateRole.show();
	}
	
}
