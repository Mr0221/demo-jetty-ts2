package com.iscc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.iscc.authority.ui.ConnectDataBase;

public class DbInfo {
	private static DbInfo readProperties;// 单例
	private ConfigInfo configInfo;

	private DbInfo() {
		configInfo = new ConfigInfo();
		getProperties();
	}

	public static DbInfo createInstance() {
		if (readProperties == null)
			readProperties = new DbInfo();
		return readProperties;
	}

	public String getDbdriver() {
		String dbdriver = null;
		if (configInfo != null) {
			dbdriver = this.configInfo.getDbdriver();
		}

		return dbdriver;
	}

	public String getDburl() {
		String dburl = null;
		if (configInfo != null) {
			dburl = this.configInfo.getDburl();
		}

		return dburl;
	}

	public String getUname() {
		String uname = null;
		if (configInfo != null) {
			uname = this.configInfo.getUname();
		}

		return uname;
	}

	public String getPwd() {
		String pwd = null;
		if (configInfo != null) {
			pwd = this.configInfo.getPwd();
		}

		return pwd;
	}

	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	/**
	 * 读取配置文件
	 */
	private void getProperties() {
		 FileInputStream fis = null;
		try {
			String uString = DbInfo.class.getResource("/").toURI().getPath()
			+ "config.properties";
	        File file = new File(uString);
	        Properties properties = new Properties();
	       
			fis = new FileInputStream(file);
			;
			properties.load(fis);

			
			configInfo.setDbdriver(properties.getProperty("dbdriver"));
			configInfo.setDburl(properties.getProperty("dbURI"));
			configInfo.setUname(properties.getProperty("uname"));
			configInfo.setPwd(properties.getProperty("pwd"));
		} catch (FileNotFoundException e) {
			System.out.println("配置文件没有找到！");
		} catch (IOException e) {
			System.out.println("输入异常！");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("关闭异常");
				}
			}
		}
	}

}

/**
 * 配置信息类
 * 
 * @author Administrator
 * 
 */
class ConfigInfo {
	private String dbdriver;
	private String dburl;
	private String uname;
	private String pwd;

	public String getDbdriver() {
		return dbdriver;
	}

	public void setDbdriver(String dbdriver) {
		this.dbdriver = dbdriver;
	}

	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
