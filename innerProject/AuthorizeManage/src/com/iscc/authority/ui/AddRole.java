package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.biz.AuthBiz;
import com.iscc.authority.biz.RoleBiz;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Role;
import com.iscc.authority.my.JSplitPane;
import com.iscc.util.Log;

public class AddRole implements ActionListener {
	JDialog dialog;
	JTabbedPane tabbedPane;
	static JTree tree;
	static DefaultMutableTreeNode node;
	JPanel bigPanel;
	JTable table;
	JTable selectTable;
	DefaultTableModel tableModel;// 下面的表格模型
	DefaultTableModel selectTableModel;// 选择的选线
	JScrollPane scrollPane2;
	List<Auth> auths;
	List<Auth> selectAuth;
	JTextField txtRoleName;//角色名
	JTextField txtRoleId;//角色ID
	public AddRole() {
		selectAuth = new ArrayList<Auth>();
		dialog = new JDialog();
		dialog.setTitle("创建角色");
		dialog.setSize(500, 500);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setResizable(false);
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(450, 150));
		bigPanel = new JPanel();
		JButton btn1 = new JButton("应用");
		btn1.addActionListener(this);
		JButton btn2 = new JButton("取消");
		JPanel panel = new JPanel();
		panel.add(btn1);
		panel.add(btn2);
		auths = new ArrayList<Auth>();
		tabbedPane = new JTabbedPane();
		JComponent panel1 = makeRoleFrame();// 往容器里显示不同的内容
		tabbedPane.addTab("一般信息", panel1);// 这里把panel换成一个fram表单
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JComponent panel2 = getAuth();
		tabbedPane.addTab("权限", panel2);// 这里把panel换成一个fram表单
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		dialog.add(tabbedPane, BorderLayout.CENTER);
		dialog.add(panel, BorderLayout.SOUTH);
	}

	protected JComponent makeRoleFrame() {
		JLabel lblRoleName = new JLabel("角色名称：");
		txtRoleName = new JTextField(25);
		txtRoleId = new JTextField(25);
		JPanel panel = new JPanel();
		
		panel.add(lblRoleName);
		panel.add(txtRoleName);
	
		return panel;
	}

	protected JComponent getAuth() {
		/********** 上面的表格 **************/
		Object[] rowValues;
		String[][] tableValues = {};
		JScrollPane scrollPane = new JScrollPane();
		String[] columnNames = { "权限ID", "权限名称" };
		tableModel = new DefaultTableModel(tableValues, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// 返回true表示能编辑，false表示不能编辑
			}
		};
		auths = getAuths();
		for (int i = 0; i < auths.size(); i++) {
			Auth auth = auths.get(i);
			String authID = auth.getaId();
			String authName = auth.getAuthName();
			rowValues = new Object[] { authID, authName };
			tableModel.addRow(rowValues);
		}

		table.setModel(tableModel);
		scrollPane.getViewport().add(table);
		table.updateUI();

		/**************** 中间的按钮 *********************/
		JButton btnSelect = new JButton("选择");
		JButton btnRemove = new JButton("移除");
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnSelect);
		btnSelect.addActionListener(new AddAuth(btnSelect));
		btnPanel.add(btnRemove);
		btnRemove.addActionListener(new AddAuth(btnRemove));

		/***************** 下面的表格 ************************/
		selectTableModel = new DefaultTableModel(tableValues, columnNames);
		selectTable = new JTable(selectTableModel);
		selectTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		scrollPane2 = new JScrollPane(selectTable);
		// selectTable.setModel(selectTableModel);
		// scrollPane2.getViewport().add(selectTable);

		bigPanel.add(scrollPane, BorderLayout.NORTH);
		bigPanel.add(btnPanel, BorderLayout.CENTER);
		bigPanel.add(scrollPane2, BorderLayout.SOUTH);
		return bigPanel;
	}

	public List<Auth> getAuths() {
		List<Auth> authList = null;
		AuthBiz authBiz = new AuthBiz();
		try {
			authList = authBiz.getAllAuth();
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
		}

		return authList;
	}

	/**
	 * 应用按钮
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String rName = txtRoleName.getText();
		RoleBiz roleBiz = new RoleBiz();
		try {
			if(rName != null && !rName.equals("")){
				roleBiz.addRoleAndAuth(rName,selectAuth);
				JOptionPane.showMessageDialog(dialog, "添加成功");
				node.removeAllChildren();
				roleBiz.getAllRole(node);// 获取所有的角色加到角色下
				tree.updateUI();
				dialog.dispose();	
			}else{
				JOptionPane.showMessageDialog(dialog, "角色名不能为空");
			}
			
		} catch (Exception e2) {
			Log.logger.error(e2.getMessage());
		}
	}

	class AddAuth implements ActionListener {
		Auth auth = new Auth();
		JButton button;

		public AddAuth(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (button.getText().equals("选择")) {
				int selectRow = table.getSelectedRow();
				String aNo = "";
				String aName = "";
				if (selectRow != -1) {
					aNo = table.getValueAt(selectRow, 0).toString();
					aName = table.getValueAt(selectRow, 1).toString();
					boolean b = false;
					if (selectAuth.size() == 0) {
						Auth auth = new Auth();
						String[] rowValues = { aNo, aName };
						selectTableModel.addRow(rowValues);
						auth.setaId(aNo);
						auth.setAuthName(aName);
						selectAuth.add(auth);
					} else if(selectAuth.size()>0){
						for (int i = 0; i < selectAuth.size(); i++) {
							Auth auth = selectAuth.get(i);
							if (aNo.equals(auth.getaId())) {
								JOptionPane.showMessageDialog(table, "您已选该权限");
								b = true;
								break;
							}	
						}
						if (b == false) {
							Auth auth = new Auth();
							auth.setaId(aNo);
							auth.setAuthName(aName);
							String[] rowValues = { aNo, aName };
							selectTableModel.addRow(rowValues);
							selectAuth.add(auth);
						}	
					}
					selectTable.setModel(selectTableModel);
					scrollPane2.validate();
				}
			}
			if (button.getText().equals("移除")) {
				int selectRow = selectTable.getSelectedRow();
				if (selectRow != -1) {
					selectAuth.remove(selectRow);
					selectTableModel.removeRow(selectRow);
					selectTable.setModel(selectTableModel);
					scrollPane2.validate();
				}
			}

		}
	}

}
