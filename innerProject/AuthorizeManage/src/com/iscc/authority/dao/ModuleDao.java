package com.iscc.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iscc.authority.entity.Module;

public class ModuleDao extends BaseDao{
	
	/**
	 * 获取功能模块的根
	 * @return
	 * @throws Exception
	 */
	public Module obtainRootModule() throws Exception{
		Module module = new Module();
		String sql = "select * from tmodule where mid='m0'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				module.setmId("m0");
				module.setMname(rs.getString("mname"));
				module.setParentModuleNo(rs.getString("parentmoduleno"));
			}
		} catch (Exception e) {
			throw e;
		}
		
		return module;
	}
	
	/**
	 * 获取功能模块下的子模块
	 * @param moduleNo
	 * @return
	 * @throws Exception
	 */
	public List<Module> getSubModule(String moduleNo) throws Exception{
		String sql = "select *from tmodule a where a.parentModuleno = '"+moduleNo+"'";
		this.OpenConncetion();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<Module> listModule = new ArrayList<Module>();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Module module = new Module();
				module.setmId(rs.getString("mid"));
				module.setMname(rs.getString("mname"));
				module.setParentModuleNo(rs.getString("parentModuleno"));
				listModule.add(module);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return listModule;
	}
}
