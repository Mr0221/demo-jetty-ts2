package com.icss.util;

public class Security {	
	/*
	 * @为了防止SQL注入攻击，禁止使用一些非法字符
	 */
	public static String getLegalSqlstr( String strInput )
	{
		String strTmp;

		strTmp = strInput;
		if( strInput != null && strInput != "")
		{
			strTmp = strTmp.replace ( "'" ,"''"); 
			strTmp = strTmp.replace(";",""); 
			strTmp = strTmp.replace("--",""); 
			
			strTmp = strTmp.replace("&",""); 			
			strTmp = strTmp.replace("%",""); 			
			strTmp = strTmp.replace("<",""); 
			strTmp = strTmp.replace(">",""); 
			strTmp = strTmp.replace("(",""); 
			strTmp = strTmp.replace(")",""); 
			strTmp = strTmp.replace("+","");			
			strTmp = strTmp.replace("/*","");
			strTmp = strTmp.replace("*/",""); 
			strTmp = strTmp.replace("delete",""); 
			strTmp = strTmp.replace("drop",""); 
			strTmp = strTmp.replace("insert",""); 
			strTmp = strTmp.replace("select",""); 
			strTmp = strTmp.replace("update",""); 
			strTmp = strTmp.replace("grant",""); 
			strTmp = strTmp.replace("alter",""); 
			strTmp = strTmp.replace("|",""); 				
		}

		return strTmp;
	}
}
