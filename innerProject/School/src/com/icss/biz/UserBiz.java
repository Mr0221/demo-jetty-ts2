package com.icss.biz;


import com.icss.dao.UserDao;
import com.icss.dto.LoginUser;
import com.icss.exception.InputNullException;
import com.icss.util.Log;
import com.icss.util.Security;

public class UserBiz {	
	
	/**
	 * 传入用户名、密码进行登录，如果登录成功就返回用户对象（包含权限信息和其它信息）
	 * @param uname
	 * @param pwd
	 * @return  要考虑的情况   1.用户名、密码为空怎么办？
	 *                   2.用户名、密码不为空，但是不正确怎么办 ------- User对象 返回为null
	 *                   3.数据库访问异常了，怎么办？                   ---------throw Exception
	 */
	public LoginUser login(String uname,String pwd) throws InputNullException,Exception{
		LoginUser user = null;
		
		Log.logger.info("uname=" + uname + "--pwd=" + pwd );
		
		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")){
			
			UserDao dao = new UserDao();
			try {
				uname = Security.getLegalSqlstr(uname);      //防止注入攻击				
				user =  dao.login(uname, pwd);			
			}catch (Exception e) {
				Log.logger.error(e.getMessage());       
				throw e;           				
			}finally{				
				 dao.closeConnection();                    //关闭数据库
			}
			
		}else{
			Log.logger.error("用户名或密码为空");       
			throw new InputNullException("用户名或密码为空，请重新输入");
		}
		
		return user;
	}			
	
}
