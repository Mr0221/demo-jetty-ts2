package com.iscc.authority.biz;

import com.iscc.util.UserInfo;

public class LoginBiz {
	
	public boolean login(String uname,String pwd) throws Exception{
		boolean b=false;
		UserInfo userInfo=UserInfo.createInstance();
		if(userInfo.getUname().equals(uname) &&userInfo.getPwd().equals(pwd)){
			b=true;
		}
		return b;
		
	}

}
