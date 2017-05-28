package com.iscc.authority.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import com.iscc.authority.ui.AddRole.AddAuth;
import com.iscc.util.Log;

public class UpdateRole implements ActionListener {
	JDialog dialog;
	JTabbedPane tabbedPane;
	static Role role;
	static JTree tree;
	static DefaultMutableTreeNode node;
	JPanel bigPanel;
	JTable table;
	JTable selectTable;
	DefaultTableModel tableModel;// ����ı��ģ��
	DefaultTableModel selectTableModel;// ѡ���ѡ��
	JScrollPane scrollPane2;
	List<Auth> auths;// ���е�Ȩ��
	List<Auth> selectAuth;// �Ѿ�ѡ���Ȩ��
	List<Auth> selfAuth;//ԭ�е�Ȩ��
	Object[] authRowValue;// ����·��ı���Ȩ��;
	JTextField txtRoleName;// ��ɫ��
	JTextField txtRoleId;// ��ɫID
	JButton btn1;
	JButton btn2;
	String[][] tableValues = {};
	//JScrollPane scrollPane = new JScrollPane();
	String[] columnNames = { "Ȩ��ID", "Ȩ������" };
	public UpdateRole() {
		selectTable = new JTable();
		selectTable.setPreferredScrollableViewportSize(new Dimension(450, 100));
		scrollPane2 = new JScrollPane(selectTable);
		selectAuth = new ArrayList<Auth>();
		dialog = new JDialog();
		dialog.setTitle("�޸Ľ�ɫ");
		dialog.setSize(500, 500);
		dialog.setLocationRelativeTo(null);
		// dialog.setVisible(true);
		dialog.setResizable(false);
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(450, 150));
		bigPanel = new JPanel();
		btn1 = new JButton("Ӧ��");
		//btn1.addActionListener(this);
		btn2 = new JButton("ȡ��");
		JPanel panel = new JPanel();
		panel.add(btn1);
		panel.add(btn2);
		auths = new ArrayList<Auth>();
		tabbedPane = new JTabbedPane();
		JComponent panel1 = makeRoleFrame();// ����������ʾ��ͬ������
		tabbedPane.addTab("һ����Ϣ", panel1);// �����panel����һ��fram��
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		JComponent panel2 = getAuth();
		tabbedPane.addTab("Ȩ��", panel2);// �����panel����һ��fram��
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		dialog.add(tabbedPane, BorderLayout.CENTER);
		dialog.add(panel, BorderLayout.SOUTH);
	}

	protected JComponent makeRoleFrame() {
		JLabel lblRoleName = new JLabel("��ɫ���ƣ�");
		txtRoleName = new JTextField(25);
		txtRoleName.setText(role.getName());
		JPanel panel = new JPanel();

		panel.add(lblRoleName);
		panel.add(txtRoleName);

		return panel;
	}

	protected JComponent getAuth() {
		/********** ����ı�� **************/
		Object[] rowValues;
		String[][] tableValues = {};
		JScrollPane scrollPane = new JScrollPane();
		String[] columnNames = { "Ȩ��ID", "Ȩ������" };
		tableModel = new DefaultTableModel(tableValues, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
			}
		};
		auths = getAuths();// ȡ�����е�Ȩ��
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
				
					  selectAuth();
				}
				else {
					return;
				}
			}
		});
		

		/***************** ����ı�� ************************/
		AuthBiz authBiz = new AuthBiz();
		//selectTableModel = new DefaultTableModel();
		selectTableModel = new DefaultTableModel(tableValues, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
			}
		};
		try {
			selfAuth = authBiz.getAuthByRoleNo(role.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int k = 0; k < selfAuth.size(); k++) {// roleLis��size�У�2��
			Auth auth = selfAuth.get(k);
			String aId = String.valueOf(auth.getaId());
			String authName = auth.getAuthName();
			authRowValue = new Object[] { aId, authName };
			selectTableModel.addRow(authRowValue);
			selectAuth.add(auth);
		}

		selectTable.setModel(selectTableModel);	
		scrollPane2.getViewport().add(selectTable);

		bigPanel.add(scrollPane, BorderLayout.NORTH);
		bigPanel.add(scrollPane2, BorderLayout.SOUTH);
		
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
	 * Ӧ�ð�ť
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String rName = txtRoleName.getText().trim();
		RoleBiz roleBiz = new RoleBiz();
		try {
			//roleBiz.addRoleAndAuth(rName, selectAuth);
			//roleBiz.updateRole(rName,selectAuth);
			node.removeAllChildren();
			roleBiz.getAllRole(node);// ��ȡ���еĽ�ɫ�ӵ���ɫ��
			JOptionPane.showMessageDialog(dialog, "�޸ĳɹ�dd");
			tree.updateUI();
			
			dialog.dispose();
		} catch (Exception e2) {
			Log.logger.error(e2.getMessage());
		}
	}

	public void show() {
		dialog.setVisible(true);
		btn1.addActionListener(this);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
	}

	public void selectAuth() {
		int selectRow = table.getSelectedRow();

		if (selectRow != -1) {
			boolean b = false;
			//boolean b = false;// ��ʶ�Ƿ��Ѿ�ѡ���˸ý�ɫ
			Auth auth = new Auth();
			String authId = table.getValueAt(selectRow, 0).toString();
			String authName = table.getValueAt(selectRow, 1).toString();
			authRowValue = new Object[] { authId, authName };
			// �ж�ѡ��Ľ�ɫ���Ƿ��и�Ȩ�ޣ�û�еĻ��ͼӽ�ȥ
		
			for(int i = 0;i<selectAuth.size();i++){
				Auth a = selectAuth.get(i);
				System.out.println("��С��"+selectAuth.size());
				System.out.println(!(a.getaId().equals(authId)));
				if (a.getaId().equals(authId)) {
					b = true;
				} 
				
			}
			
			if(b == false){
				auth.setaId(authId);
				auth.setAuthName(authName);
				
				selectAuth.add(auth);
				selectTableModel.addRow(authRowValue);		
			}else {
				JOptionPane.showMessageDialog(dialog, "����ѡ���Ȩ��");
			}
			selectTable.setModel(selectTableModel);
			selectTable.validate();
			scrollPane2.getViewport().add(selectTable);
			//selectTable = new JTable();
		}
		//scrollPane2.validate();
		

	}
	

	/**
	 * �Ƴ�ѡ���Ȩ��
	 */
	public void removeAuth() {
		int selectRow = selectTable.getSelectedRow();
		if (selectRow != -1) {
			// System.out.println(selectAuth.remove(selectRow));//�Ƴ��������õ���selecRow
			/*Auth auth = new Auth();
			String authId = selectTable.getValueAt(selectRow, 0).toString();
			String authName = selectTable.getValueAt(selectRow, 1).toString();*/
			
			selectTableModel.removeRow(selectRow);
			selectTable.setModel(selectTableModel);
			scrollPane2.getViewport().add(selectTable);
			scrollPane2.validate();
			selectTable.validate();
			selectAuth.remove(selectRow);
		}
	}
}
