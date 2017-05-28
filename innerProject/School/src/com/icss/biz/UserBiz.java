package com.icss.biz;


import com.icss.dao.UserDao;
import com.icss.dto.LoginUser;
import com.icss.exception.InputNullException;
import com.icss.util.Log;
import com.icss.util.Security;

public class UserBiz {	
	
	/**
	 * �����û�����������е�¼�������¼�ɹ��ͷ����û����󣨰���Ȩ����Ϣ��������Ϣ��
	 * @param uname
	 * @param pwd
	 * @return  Ҫ���ǵ����   1.�û���������Ϊ����ô�죿
	 *                   2.�û��������벻Ϊ�գ����ǲ���ȷ��ô�� ------- User���� ����Ϊnull
	 *                   3.���ݿ�����쳣�ˣ���ô�죿                   ---------throw Exception
	 */
	public LoginUser login(String uname,String pwd) throws InputNullException,Exception{
		LoginUser user = null;
		
		Log.logger.info("uname=" + uname + "--pwd=" + pwd );
		
		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")){
			
			UserDao dao = new UserDao();
			try {
				uname = Security.getLegalSqlstr(uname);      //��ֹע�빥��				
				user =  dao.login(uname, pwd);			
			}catch (Exception e) {
				Log.logger.error(e.getMessage());       
				throw e;           				
			}finally{				
				 dao.closeConnection();                    //�ر����ݿ�
			}
			
		}else{
			Log.logger.error("�û���������Ϊ��");       
			throw new InputNullException("�û���������Ϊ�գ�����������");
		}
		
		return user;
	}			
	
}
