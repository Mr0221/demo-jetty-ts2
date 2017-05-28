package com.iscc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class UserInfo {
	private String uname;
	private String pwd;
	
	private static UserInfo userInfo;

	public UserInfo() {
		this.readProperties();
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


	public static UserInfo createInstance() {
		if (userInfo == null)
			userInfo = new UserInfo();
		return userInfo;
	}

	/**
	 * ��ȡ�����ļ���Ϣ
	 * 
	 */
	public void readProperties() {
		Properties properties = new Properties();
		FileInputStream fis = null;
		String path;
		try {
			path = UserInfo.class.getResource("/").toURI().getPath()+ "userlogin.properties";
			File file = new File(path);
			fis = new FileInputStream(file);
			properties.load(fis);
			uname = properties.getProperty("uname");
			pwd = properties.getProperty("pwd");
		} catch (URISyntaxException e) {
			Log.logger.info(e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Log.logger.info(e.getMessage() + "�����ļ�û�ҵ�");
		} catch (IOException e) {
			Log.logger.info(e.getMessage() + "��ȡ�쳣");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.logger.info(e.getMessage());
				}

			}
		}

	}

}
