package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.biz.AuthBiz;
import com.iscc.authority.biz.ModuleBiz;
import com.iscc.authority.biz.RoleBiz;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Module;
import com.iscc.util.Log;

public class UpdateAddAuth extends JFrame implements TreeSelectionListener {
	private JTree tree;
	private JSplitPane split;
	private JPanel rightPanel;
	JPanel selectAuthPanel;// 已经选择的权限
	JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	JLabel lblAuth;
	String txt = "";
	int i = 0;
	/************** AddUserFrame需要用的所有 ******************/
	List<Auth> selectAuth = new ArrayList<Auth>(); // 装要添加的权限
	DefaultTableModel selectTableModel;// 下方表格的model
	JTable selectTable;// 存放选中的权限《下方表格》
	/***************************************************/

	JScrollPane selectPane;// 存放选中的表格

	public UpdateAddAuth() {
		this.setLayout(null);
		table = new JTable();
		selectTable = new JTable();
		selectTable.setPreferredScrollableViewportSize(new Dimension(360, 100));
		table.setPreferredScrollableViewportSize(new Dimension(300, 35));
		rightPanel = new JPanel(new BorderLayout());
		scrollPane = new JScrollPane(table);
		selectPane = new JScrollPane(selectTable);
		selectAuthPanel = new JPanel();

	}

	protected JComponent addAuth() {

		String[][] selectTableValues = {};
		String[] selectColumnName = { "权限ID", "权限名称" };
		selectTableModel = new DefaultTableModel(selectTableValues,selectColumnName) {
			public boolean isCellEditable(int row, int column) {
				return false;// 返回true表示能编辑，false表示不能编辑
			}
		};

		JPanel panel = new JPanel(new BorderLayout());
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("对象权限管理");// 总节点

		this.tree = new JTree(top);

		createNodes(top);// 创建节点

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree, rightPanel);// 垂直方向分裂
		split.setDividerSize(8);// 设置分隔条的宽度
		split.setDividerLocation(160);// 设置分割条的位置
		JScrollPane splitScrollPane = new JScrollPane(split);

		// 选择的角色标题栏
		selectAuthPanel.setBorder(BorderFactory.createTitledBorder("选择的权限"));
		selectAuthPanel.setForeground(Color.red);
		selectAuthPanel.setPreferredSize(new Dimension(400, 250));

		
		/*rightPanel.add(label);*/
		rightPanel.add(selectAuthPanel, BorderLayout.SOUTH);

		/****** 下方的radiobutton *********/
		JPanel panel2 = new JPanel();
		JLabel warnLabel = new JLabel("注：需要添加或移除选中的权限时，鼠标双击即可");
		warnLabel.setForeground(Color.red);
		panel2.add(warnLabel);

		panel.add(splitScrollPane, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);

		tree.updateUI();
		tree.addTreeSelectionListener(this);
		return panel;

	}

	/**
	 * 创建节点
	 * 
	 * @param top
	 */
	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode module = null;
		ModuleBiz moduleBiz = new ModuleBiz();

		try {
			module = new DefaultMutableTreeNode(moduleBiz.obtainRootModule());

			moduleBiz.getSubModule(module);

			top.add(module);

		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		Object auth = node.getUserObject();// 获取权限实体，根据实体的名显示不同的表格
		Module module = (Module) auth;
		AuthBiz authBiz = new AuthBiz();
		if (node.isLeaf()) {
			String mid = module.getmId();// 获取选中结点的id
			List<Auth> authList = null;
			try {
				authList = authBiz.getAuthList(mid);// 获取该结点所拥有的权限
				new ShowTable(authList);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	class ShowTable extends MouseAdapter {

		Object[] rowValues;
		Object[] selectRowValues;// 下面的表格
		private List<Auth> authList;

		JCheckBox ck;

		public ShowTable(List<Auth> authList) {// 注意，要显示的内容不要在里面new 不然会不显示
			String[][] tableValues = {};
			String[] columnNames = { "权限ID", "权限名称" };

			this.authList = authList;
			tableModel = new DefaultTableModel(tableValues, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return false;// 返回true表示能编辑，false表示不能编辑
				}
			};
			getAuth();
			table.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						selectAuth();
					}
				}
			});
			
			selectTable.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						removeAuth();
					}
				}
			});

		}

		public void getAuth() {
			if (authList != null) {
				for (int i = 0; i < authList.size(); i++) {
					Auth auth = authList.get(i);
					String authID = auth.getaId();
					String authName = auth.getAuthName();
					rowValues = new Object[] { authID, authName };
					tableModel.addRow(rowValues);
				}

				table.setModel(tableModel);
				table.updateUI();

			} else {
				table.removeAll();
			}
			rightPanel.add(scrollPane, BorderLayout.NORTH);
			rightPanel.validate();
			// selectAuth();
		}

		/**
		 * 获取选中的权限，加入选择的权限表格中
		 */
		public void selectAuth() {
			int selectRow = table.getSelectedRow();

			if (selectRow != -1) {
				boolean b = false;// 标识是否已经选择了该角色
				Auth auth = new Auth();
				String authId = table.getValueAt(selectRow, 0).toString();
				String authName = table.getValueAt(selectRow, 1).toString();
				selectRowValues = new Object[] { authId, authName };
					// 判断选择的角色中是否有该权限，没有的话就加进去
					if (UpdateEmploy.authMap.get(authId) == null ) {						
						auth.setaId(authId);
						auth.setAuthName(authName);
						selectAuth.add(auth);
						selectTableModel.addRow(selectRowValues);
						selectTable.setModel(selectTableModel);
						tableModel.removeRow(selectRow);
						table.setModel(tableModel);
						
						UpdateEmploy.authMap.put(auth.getaId(), auth
								.getAuthName());
						UpdateEmploy.selectRoleAuthModel
								.addRow(selectRowValues);
						UpdateEmploy.commonTable
								.setModel(UpdateEmploy.selectRoleAuthModel);
						UpdateEmploy.selecyRoleAuthPane.getViewport().add(
								UpdateEmploy.commonTable);
						UpdateEmploy.roleAuthPanel
								.add(UpdateEmploy.selecyRoleAuthPane);						
						
						UpdateEmploy.selecyRoleAuthPane.validate();// 刷新
						UpdateEmploy.roleAuthPanel.validate();
					}else {				
						/*label.setVisible(true);
						//JOptionPane.showMessageDialog(selectTable, "角色中已有该权限");
						label.setText("你已选");
						label.setVisible(false);*/
					}
					
					
			}
			selectAuthPanel.add(selectPane);
			selectAuthPanel.validate();
		}

		/**
		 * 移除选择的权限
		 */
		public void removeAuth() {
			int selectRow = selectTable.getSelectedRow();
			if (selectRow != -1) {
				// System.out.println(selectAuth.remove(selectRow));//移除！！！用的是selecRow
				Auth auth = new Auth();
				String authId = selectTable.getValueAt(selectRow, 0).toString();
				String authName = selectTable.getValueAt(selectRow, 1).toString();
				
				selectTableModel.removeRow(selectRow);
				selectAuthPanel.validate();
				selectAuth.remove(selectRow);
				//必须要一个一个的移除modoel中的数据，我真的快疯了！！！！！！！！！
//				for(int i=0;i< userFrame.authMap.size();i++){
//					UpdateEmploy.selectRoleAuthModel.removeRow(0);
//				}
				//同时移除map中的选择的权限
				UpdateEmploy.authMap.remove(authId);//移除Map中的
				
				//重新查询遍历一遍结合
				for (String key : UpdateEmploy.authMap.keySet()) {
					String auName = UpdateEmploy.authMap.get(key);
					String[] rowValue = { key, auName };
					UpdateEmploy.selectRoleAuthModel.addRow(rowValue);
				}

				UpdateEmploy.commonTable.setModel(UpdateEmploy.selectRoleAuthModel);
				UpdateEmploy.selecyRoleAuthPane.getViewport().add(UpdateEmploy.commonTable);
				UpdateEmploy.roleAuthPanel.add(UpdateEmploy.selecyRoleAuthPane);						
		       
				UpdateEmploy.selecyRoleAuthPane.validate();// 刷新
				UpdateEmploy.roleAuthPanel.validate();
			}
		}
	}

}
