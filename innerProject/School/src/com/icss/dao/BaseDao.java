package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.icss.util.DbInfo;

public class BaseDao {
	
	protected Connection conn;
	
	public void openConnection() throws Exception{
		try {
			if(this.conn == null){
				DbInfo db = DbInfo.instance();
				Class.forName(db.getDbdriver());        //动态装载数据库驱动
				conn = DriverManager.getConnection(db.getUrl(), db.getUser(),db.getPassword());					
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	public void closeConnection() {
		if(this.conn != null ){
			try {
				this.conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	/**
	 * 打开事务
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);         //开启手动提交事务的模式		
	}
	
	/**
	 * 提交事务
	 * @throws Exception
	 */
	public void commit() throws Exception{		
		if(this.conn != null){			
			this.conn.commit();
		}		
	}
	
	/**
	 * 回滚事务
	 * @throws Exception
	 */
	public void rollback () throws Exception{
		if(this.conn != null){
			this.conn.rollback();
		}
	}

}
 