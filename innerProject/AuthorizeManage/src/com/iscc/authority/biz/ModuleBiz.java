package com.iscc.authority.biz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.iscc.authority.dao.AuthDao;
import com.iscc.authority.dao.ModuleDao;
import com.iscc.authority.entity.Auth;
import com.iscc.authority.entity.Module;

public class ModuleBiz {
	
	
	public Module obtainRootModule() throws Exception{
		ModuleDao moduleDao = new ModuleDao();
		Module module = null;
		try {
			module = moduleDao.obtainRootModule();
		} catch (Exception e) {
			throw e;
		} finally{
			moduleDao.closeConnection();
		}
		return module;
	}
	
	
	/**
	 * �ݹ��ѯ�ӹ���ģ��
	 * @param node
	 * @throws Exception
	 */
	public void getSubModule(DefaultMutableTreeNode node) throws Exception{
		Object object = node.getUserObject();
		List<Module> listMoudle = null;
		
		if(object instanceof Module){
			Module module = (Module)object;
			ModuleBiz moduleBiz = new ModuleBiz();
			listMoudle = moduleBiz.getSubModule(module.getmId());
			if(listMoudle != null){
				for(Module m:listMoudle){
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(m);
					node.add(childNode);
					getSubModule(childNode);
				}
			}
		}
	}
	
	/**
	 * ����ĳ�����ŵĲ��źŻ�ȡ�Ӳ���
	 * @param moduleNo
	 * @return
	 */
	public List<Module> getSubModule(String moduleNo)throws Exception{
		ModuleDao moduleDao = new ModuleDao();
		List<Module> list = new ArrayList<Module>();
		try {
			list = moduleDao.getSubModule(moduleNo);
		} catch (Exception e) {
			throw e;
		}finally{
			moduleDao.closeConnection();
		}
		return list;
	}
}
