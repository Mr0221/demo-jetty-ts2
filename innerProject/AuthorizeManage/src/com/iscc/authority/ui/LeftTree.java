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
	// �ڵ�
	public JTree tree;
	private JSplitPane split;
	// private JTextArea area;

	/******* ���Ŀؼ� *********/
	DefaultTableModel tableModel;

	private JScrollPane rightPane;// �Ҳ��pane,��ű��

	private JPanel panel;
	private JPopupMenu popMenu;// �Ҽ��˵�
	private JTable table;// �Ҳ�ı��

	DefaultTableCellRenderer tcr;// ������
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
		/*********** �˵��� **********/
		jmb = new JMenuBar();

		file = new JMenu("�ļ�");
		util = new JMenu("����");
		newfile = new JMenu("�½�");

		user = new JMenuItem("�û�");
		seluser = new JMenuItem("�û���ѯ");
		dep = new JMenuItem("����");
		people = new JMenuItem("��Ա");
		role = new JMenuItem("��ɫ");
		database = new JMenuItem("�������ݿ�");
		
		jmb.add(file);
		jmb.add(util);

		file.add(user);
		file.add(newfile);
		file.add(seluser);

		util.add(database);
		newfile.add(dep);
		newfile.add(people);
		newfile.add(role);

		// ��Ӳ˵�
		this.add(jmb, BorderLayout.NORTH);

		/********* �ڵ� ************/
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("����Ȩ�޹���");// �ܽڵ�

		this.tree = new JTree(top);
		treePane.getViewport().add(tree);
		rightPane = new JScrollPane(table);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, rightPane);// ��ֱ�������
		split.setDividerSize(8);// ���÷ָ����Ŀ��

		split.setDividerLocation(150);
		this.setTitle("Ȩ�޹���");
		this.getContentPane().add(split);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//��Ի���Ϊ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ݾ���
		tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		database.addActionListener(new ConnectDataBase());
		createNodes(top);// �����ڵ�

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				createPopupMenu();
				showDeptTable();// ��ʾ���
			}
		});

		/******* �����Ҽ��˵�,�����Ӽ��� ******/
		tree.addMouseListener(new TreePopMenuEvent(this));// ����!!!!!Ϊ�����Ӽ���
		tree.updateUI();

	}

	/**
	 * �����ڵ�
	 * 
	 * @param top
	 */
	public void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode department = null;// ���Ÿ��ڵ�
		DefaultMutableTreeNode subRole = null;// ��ɫ�ӽڵ�
		DepartmentBiz deptBiz = new DepartmentBiz();
		RoleBiz roleBiz = new RoleBiz();
		try {
			department = new DefaultMutableTreeNode(deptBiz.obtainRootDepart());// ��ȡ�����Ӳ��ŵ��ܸ���"����"

			deptBiz.getSubDept(department);// ��ȡ�ӽ��

			top.add(department);// �ܽڵ�top�µĵ�һ���ӽڵ㡪������
            Role role = new Role();
            role.setId(0);
            role.setName("���н�ɫ");
			role1 = new DefaultMutableTreeNode(role);
			roleBiz.getAllRole(role1);					// ��ȡ���еĽ�ɫ�ӵ���ɫ��

			top.add(role1);								// �ܽڵ��µĵڶ����ӽڵ�
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}

	}

	/**
	 * �����Ҽ��Ĳ˵�
	 */
	public void createPopupMenu() {

		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();// ��ȡ��ѡ�еĽڵ�

		Object object;

		if (selectedNode == null|| selectedNode.getUserObject().equals("����Ȩ�޹���")) {
			return;
		} else if (/* object != null && */!selectedNode.isRoot()) {
			object = selectedNode.getUserObject();
			String nodeType = "";
			boolean isChild = false;
			if (object instanceof Department) {						// ���ѡ�еĽ���ǲ�������
				Department dept = (Department) object;
				nodeType = "dept";// �������Ϊ����
				if (dept.getParentDept() != null) {
					isChild = true;// ���ӽ��
				}
			} else if (object instanceof Role) {					// ���ѡ�еĽ���ǽ�ɫ����
				nodeType = "role";
			} else if (object instanceof Employee) {// ���ѡ�еĽ����Ա������
				nodeType = "employee";
			}

			if (nodeType.equals("dept") && isChild == false) {// ���ѡ����ǲ��Ž�㣬ֻ�д����Ӳ���һ���˵�
				popMenu = new JPopupMenu();
				JMenuItem addDept = new JMenuItem("����Ӳ���");
				addDept.addActionListener(new TreeAddDeptMenuEvent(this));     // ����Ӳ��ŵļ���
				popMenu.add(addDept);

			} else if (nodeType.equals("role")) {    // ���ѡ����ǽ�ɫ���
				Role role = (Role)object;
				if(role.getName().equals("���н�ɫ")){
					popMenu = new JPopupMenu();
					JMenuItem menu = new JMenuItem("������ɫ");
					popMenu.add(menu);
					menu.addActionListener(new TreeAddRoleMenuEvent(this));// ��Ӵ�����ɫ�ļ���	
				}else{
					popMenu = new JPopupMenu();
					JMenuItem menu = new JMenuItem("�༭");//�༭��ɫ
					JMenuItem menu2 = new JMenuItem("ɾ��");//ɾ����ɫ
					popMenu.add(menu);
					menu.addActionListener(new TreeUpdateRole(this));// ��Ӵ�����ɫ�ļ���	
				}
			} else if (nodeType.equals("dept") && isChild == true) {// Ϊ�����µ��Ӳ���ʱ������
				popMenu = new JPopupMenu();
				JMenuItem addDept = new JMenuItem("����Ӳ���");
				JMenuItem addUser = new JMenuItem("���Ա��");
				JMenuItem updateDept = new JMenuItem("�༭");
				JMenuItem delDept = new JMenuItem("ɾ��");
				popMenu.add(addDept);
				addDept.addActionListener(new TreeAddDeptMenuEvent(this));// ����Ӳ��ŵļ���
				popMenu.add(addUser);
				addUser.addActionListener(new TreeAddUserMenuEvent(this));// ����û��ļ���
				popMenu.add(updateDept);
				updateDept.addActionListener(new TreeUpdateDept(this));
				popMenu.add(delDept);
				delDept.addActionListener(new TreeDelDept(this));// ɾ������
			} else if (nodeType.equals("employee")) {
				popMenu = new JPopupMenu();
				JMenuItem updateEmp = new JMenuItem("�༭");
				updateEmp.addActionListener(new TreeUpdateEmploy(this));
				JMenuItem delEmp = new JMenuItem("ɾ��");
				popMenu.add(updateEmp);
				popMenu.add(delEmp);
				delEmp.addActionListener(new TreeDelDept(this));
			}
		} else if (selectedNode.isRoot()) {
			return;
		}
		// rightPane.removeAll();//����Ҳ�����ж���
	}

	public void actionPerformed(ActionEvent e) {

		new AddDepartFrame();
	}

	public void valueChanged(TreeSelectionEvent e) {

	}

	/**
	 * �������ʱ����ʾ���ź������Ӳ����б�
	 */
	public void showDeptTable() {
		DepartmentBiz deptBiz = new DepartmentBiz();
		DefaultMutableTreeNode selecNode = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (selecNode != null) {
			Object object = selecNode.getUserObject();
			if (object instanceof Department) {// ���Žڵ�
				Department selectedDept = (Department) object;
				String[] aa = { "���ű��", "��������", "���ż��" };
				columnNames = aa;
				try {
					List<Department> listDept = deptBiz.getAllSubDept(selectedDept.getDeptNo());     //���ݲ��ű�Ų��ҳ��ò����µ��Ӳ���List
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

			} else if (object instanceof Employee) {// ��Ա�ڵ�
				Employee employee = (Employee) object;
				new showUserTable(employee);
			}

			rightPane.repaint();// ��validate����ʹ
		}
	}

	/********************** ��ʾ�û���Ϣ���ڲ��� ************************/
	/**
	 * ������������Ҳ�ͨ�������ʾ��Ϣ
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
			JComponent panel1 = getUser();// ����������ʾ��ͬ������
			tabbedPane.addTab("һ����Ϣ", panel1);// �����panel����һ��fram��
			panel1.getComponents();
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			JComponent panel2 = getRole();// ����������ʾ��ͬ������
			tabbedPane.addTab("��ɫ", panel2);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			JComponent panel3 = getAuth();// ����������ʾ��ͬ������
			tabbedPane.addTab("Ȩ��", panel3);
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

			rightPane.getViewport().add(tabbedPane);
		}

		/**
		 * ��ȡһ����Ϣ
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getUser() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "Ա�����", "Ա������", "���ڲ���", "�Ա�" };
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
		 * ��ȡ��ɫ��Ϣ
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getRole() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "��ɫ���", "��ɫ��" };
			columnNames = aa;
			tableModel = new DefaultTableModel(tableValues, aa);
			table.setDefaultRenderer(Object.class, tcr);
			RoleBiz roleBiz = new RoleBiz();
			try {
				List<Role> roles = roleBiz.getRole(emp.getEno());// �����û���Ų�ѯ��ӵ�еĽ�ɫ
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
		 * ��ȡȨ����Ϣ
		 * 
		 * @param employee
		 * @return
		 */
		public JComponent getAuth() {
			table = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			String[] aa = { "Ȩ�ޱ��", "Ȩ����" };
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
 * Ϊ����Ӽ�������
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

	public void mouseReleased(MouseEvent e) {// �ͷ�����ʱ��

		TreePath path = adaptee.getTree()
				.getPathForLocation(e.getX(), e.getY());// �ؼ������������ʹ��
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
 * �Ҽ�����Ӳ���ʱ
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

		AddDepartFrame deptFrame = null;// ����Ӳ��ŶԻ���
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��

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
 * �Ҽ����Ա��
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
				.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��

		AddUserFrame addUserFrame = null;
		if (selectNode == null) {
		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Department) {
				Department department = (Department) object;
				AddUserFrame.dept = department;// �ѵ�ǰѡ�еĲ��Ŵ���ȥ
				AddUserFrame.tree = leftTree.getTree();// �ѵ�ǰ������ȥ�����ڸ���
				AddUserFrame.selectNode = selectNode;// �ѵ�ǰ�Ľ�㴫��ȥ���������²���
				addUserFrame = new AddUserFrame();// �༭���ŶԻ���
			}
			addUserFrame.show();
		}
	}

}

/**
 * �Ҽ���ӽ�ɫ
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
 * �༭Ա��
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
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��
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
 * �༭����
 */
class TreeUpdateDept implements ActionListener {

	private LeftTree leftTree;

	public TreeUpdateDept(LeftTree leftTree) {
		this.leftTree = leftTree;
	}

	public void actionPerformed(ActionEvent e) {
		UpdateDept deptFrame = null;
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) (leftTree
				.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��

		DefaultMutableTreeNode selectParentNode = (DefaultMutableTreeNode) selectNode.getParent();
		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Department) {
				Department department = (Department) object;
				UpdateDept.department = department;
				UpdateDept.tree = leftTree.getTree();
				UpdateDept.selectNode = selectParentNode;
				deptFrame = new UpdateDept();// �༭���ŶԻ���

			}
		}
		deptFrame.show();
	}

}

/**
 * ɾ������
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
				.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��
		DefaultTreeModel model = (DefaultTreeModel) leftTree.getTree()
				.getModel(); // ��ȡ��ģ��

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
				JOptionPane.showMessageDialog(leftTree.getTree(), "ɾ���ɹ�");
				leftTree.getRightPane().repaint();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(leftTree.getTree(), "����ɾ���Ӽ�¼");
				Log.logger.error("��ɾ���Ӽ�¼����ɾ������¼");
			}
		}
		if (object instanceof Employee) {// ɾ����Ա
			emp = (Employee) object;
			try {
				empBiz.delEmp(emp);
				model.removeNodeFromParent(selectNode);
				JOptionPane.showMessageDialog(leftTree.getTree(), "ɾ���ɹ�");
				leftTree.getTable().validate();
				leftTree.getRightPane().validate();
			} catch (Exception e2) {
				Log.logger.error(e2.getMessage());
			}
		}
	}

}
/**
 * �༭��ɫ
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
				.getTree()).getLastSelectedPathComponent();// ��ȡѡ��Ľ��

		if (selectNode == null) {

		} else {
			Object object = selectNode.getUserObject();
			if (object instanceof Role) {
				Role role = (Role) object;
				UpdateRole.role = role;
				UpdateRole.tree = leftTree.getTree();
				UpdateRole.node = selectNode;
				updateRole = new UpdateRole();// �༭���ŶԻ���

			}
		}
		updateRole.show();
	}
	
}
