package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DbInfo {
	
	private String dbdriver;
	private String url;
	private String user;
	private String password;
	
	private static DbInfo dbinfo;     //让它成为单例
	
	private DbInfo(){
		
		Properties prop = new Properties();
		String path = DbInfo.class.getResource("/").getPath() + "db.properties";
		try {
			prop.load(new FileInputStream(new File(path)));
			this.dbdriver = prop.getProperty("dbdriver");
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("user");
			this.password = prop.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 用静态方法创建对象
	 * @return
	 */
	public static DbInfo instance(){
		
		if(dbinfo == null)
			dbinfo = new DbInfo();
		
		return dbinfo;
	}
	

	public String getDbdriver() {
		return dbdriver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	

}
