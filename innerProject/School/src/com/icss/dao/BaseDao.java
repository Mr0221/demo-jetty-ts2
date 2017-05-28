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
				Class.forName(db.getDbdriver());        //��̬װ�����ݿ�����
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
	 * ������
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);         //�����ֶ��ύ�����ģʽ		
	}
	
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public void commit() throws Exception{		
		if(this.conn != null){			
			this.conn.commit();
		}		
	}
	
	/**
	 * �ع�����
	 * @throws Exception
	 */
	public void rollback () throws Exception{
		if(this.conn != null){
			this.conn.rollback();
		}
	}

}
 