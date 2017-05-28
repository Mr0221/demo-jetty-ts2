package com.iscc.authority.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.iscc.util.DbInfo;




public abstract class BaseDao {
	
protected Connection connection;	
	
	public Connection getConnection() throws Exception {
		this.OpenConncetion();
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void OpenConncetion() throws Exception{
		try {
			if(connection == null || connection.isClosed()){
				
				DbInfo dbInfo = DbInfo.createInstance();				
				Class.forName(dbInfo.getDbdriver());	    //反射检查驱动包是否存在
				connection = DriverManager.getConnection(dbInfo.getDburl(),dbInfo.getUname(),dbInfo.getPwd());	
			}			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}		
		
	}
	
	/**
	 * 此处的异常，不需抛出，只需要记录
	 */
	public void closeConnection(){
		
		if( connection != null){
			try {
				if(!connection.isClosed())
					connection.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void beginTransaction() throws Exception{		
		this.OpenConncetion();
		this.connection.setAutoCommit(false);		
	}
	
	public void commit() throws Exception{
		
		if(this.connection != null)
			this.connection.commit();
		
	}
	
	public void rollback() throws Exception{
		if(this.connection != null){
			this.connection.rollback();
		}		
	}
	
}
