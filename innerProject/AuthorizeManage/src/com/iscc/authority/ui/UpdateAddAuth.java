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
	JPanel selectAuthPanel;// �Ѿ�ѡ���Ȩ��
	JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel tableModel;
	JLabel lblAuth;
	String txt = "";
	int i = 0;
	/************** AddUserFrame��Ҫ�õ����� ******************/
	List<Auth> selectAuth = new ArrayList<Auth>(); // װҪ��ӵ�Ȩ��
	DefaultTableModel selectTableModel;// �·�����model
	JTable selectTable;// ���ѡ�е�Ȩ�ޡ��·����
	/***************************************************/

	JScrollPane selectPane;// ���ѡ�еı��

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
		String[] selectColumnName = { "Ȩ��ID", "Ȩ������" };
		selectTableModel = new DefaultTableModel(selectTableValues,selectColumnName) {
			public boolean isCellEditable(int row, int column) {
				return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
			}
		};

		JPanel panel = new JPanel(new BorderLayout());
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("����Ȩ�޹���");// �ܽڵ�

		this.tree = new JTree(top);

		createNodes(top);// �����ڵ�

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree, rightPanel);// ��ֱ�������
		split.setDividerSize(8);// ���÷ָ����Ŀ��
		split.setDividerLocation(160);// ���÷ָ�����λ��
		JScrollPane splitScrollPane = new JScrollPane(split);

		// ѡ��Ľ�ɫ������
		selectAuthPanel.setBorder(BorderFactory.createTitledBorder("ѡ���Ȩ��"));
		selectAuthPanel.setForeground(Color.red);
		selectAuthPanel.setPreferredSize(new Dimension(400, 250));

		
		/*rightPanel.add(label);*/
		rightPanel.add(selectAuthPanel, BorderLayout.SOUTH);

		/****** �·���radiobutton *********/
		JPanel panel2 = new JPanel();
		JLabel warnLabel = new JLabel("ע����Ҫ��ӻ��Ƴ�ѡ�е�Ȩ��ʱ�����˫������");
		warnLabel.setForeground(Color.red);
		panel2.add(warnLabel);

		panel.add(splitScrollPane, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);

		tree.updateUI();
		tree.addTreeSelectionListener(this);
		return panel;

	}

	/**
	 * �����ڵ�
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

		Object auth = node.getUserObject();// ��ȡȨ��ʵ�壬����ʵ�������ʾ��ͬ�ı��
		Module module = (Module) auth;
		AuthBiz authBiz = new AuthBiz();
		if (node.isLeaf()) {
			String mid = module.getmId();// ��ȡѡ�н���id
			List<Auth> authList = null;
			try {
				authList = authBiz.getAuthList(mid);// ��ȡ�ý����ӵ�е�Ȩ��
				new ShowTable(authList);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	class ShowTable extends MouseAdapter {

		Object[] rowValues;
		Object[] selectRowValues;// ����ı��
		private List<Auth> authList;

		JCheckBox ck;

		public ShowTable(List<Auth> authList) {// ע�⣬Ҫ��ʾ�����ݲ�Ҫ������new ��Ȼ�᲻��ʾ
			String[][] tableValues = {};
			String[] columnNames = { "Ȩ��ID", "Ȩ������" };

			this.authList = authList;
			tableModel = new DefaultTableModel(tableValues, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
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
		 * ��ȡѡ�е�Ȩ�ޣ�����ѡ���Ȩ�ޱ����
		 */
		public void selectAuth() {
			int selectRow = table.getSelectedRow();

			if (selectRow != -1) {
				boolean b = false;// ��ʶ�Ƿ��Ѿ�ѡ���˸ý�ɫ
				Auth auth = new Auth();
				String authId = table.getValueAt(selectRow, 0).toString();
				String authName = table.getValueAt(selectRow, 1).toString();
				selectRowValues = new Object[] { authId, authName };
					// �ж�ѡ��Ľ�ɫ���Ƿ��и�Ȩ�ޣ�û�еĻ��ͼӽ�ȥ
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
						
						UpdateEmploy.selecyRoleAuthPane.validate();// ˢ��
						UpdateEmploy.roleAuthPanel.validate();
					}else {				
						/*label.setVisible(true);
						//JOptionPane.showMessageDialog(selectTable, "��ɫ�����и�Ȩ��");
						label.setText("����ѡ");
						label.setVisible(false);*/
					}
					
					
			}
			selectAuthPanel.add(selectPane);
			selectAuthPanel.validate();
		}

		/**
		 * �Ƴ�ѡ���Ȩ��
		 */
		public void removeAuth() {
			int selectRow = selectTable.getSelectedRow();
			if (selectRow != -1) {
				// System.out.println(selectAuth.remove(selectRow));//�Ƴ��������õ���selecRow
				Auth auth = new Auth();
				String authId = selectTable.getValueAt(selectRow, 0).toString();
				String authName = selectTable.getValueAt(selectRow, 1).toString();
				
				selectTableModel.removeRow(selectRow);
				selectAuthPanel.validate();
				selectAuth.remove(selectRow);
				//����Ҫһ��һ�����Ƴ�modoel�е����ݣ�����Ŀ���ˣ�����������������
//				for(int i=0;i< userFrame.authMap.size();i++){
//					UpdateEmploy.selectRoleAuthModel.removeRow(0);
//				}
				//ͬʱ�Ƴ�map�е�ѡ���Ȩ��
				UpdateEmploy.authMap.remove(authId);//�Ƴ�Map�е�
				
				//���²�ѯ����һ����
				for (String key : UpdateEmploy.authMap.keySet()) {
					String auName = UpdateEmploy.authMap.get(key);
					String[] rowValue = { key, auName };
					UpdateEmploy.selectRoleAuthModel.addRow(rowValue);
				}

				UpdateEmploy.commonTable.setModel(UpdateEmploy.selectRoleAuthModel);
				UpdateEmploy.selecyRoleAuthPane.getViewport().add(UpdateEmploy.commonTable);
				UpdateEmploy.roleAuthPanel.add(UpdateEmploy.selecyRoleAuthPane);						
		       
				UpdateEmploy.selecyRoleAuthPane.validate();// ˢ��
				UpdateEmploy.roleAuthPanel.validate();
			}
		}
	}

}
