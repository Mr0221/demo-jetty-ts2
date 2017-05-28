package com.icss.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.security.*;

public class SysUtility {
		 
	/**
	 * 字符串输出显示时，一个中文占两个位置，一个英文或数字占一个位置
	 * @param strInput
	 * @return
	 */
	 public static int getDisplayPosLen(String strInput){
		 int iLen = 0;
		 
		 if(strInput == null || strInput.length()<= 0){   
			  return  0;   
		 }			 
		 char   c;   
		 for(int i=0;i<strInput.length();i++){   
			 c = strInput.charAt(i);  
			 if( IsChineseChar(c)){
				 iLen += 2;
			 }
			 else{
				 iLen += 1;
			 }			 
		 }     
		    
		 return  iLen;	 
	 }
	 
	
	 /**
	  *  判断某个字符是否为中文字符 
	  * @param c
	  * @return
	  */
	 public static boolean IsChineseChar(char c){
		 boolean bRet;
		 
		 //ASCII码的范围是0~255
		 if(c>255 || c<0){
			 bRet = true;
		 }
		 else
		 	bRet = false;
		 
		 return bRet;	
	 }
	 
	 
	 /**
	  * 获得指定长度的字符串 --iMaxLen为最多提取的英文字符长度--一个中文字占两个英文字长度 
	  * @param strInput
	  * @param iMaxLen
	  * @return
	  */
	 public static String getSubString(String strInput,int iMaxLen){
		 String strRet;
		 int iSum;
		 
		 strRet = "";
		 iSum = 0;
		 if(strInput != null){
			 char   c;   
			 for(int i=0;i<strInput.length();i++){   
				 c = strInput.charAt(i);  
				 if( IsChineseChar(c))
					 iSum += 2;				 
				 else
					 iSum += 1;				 
				 if(iSum > iMaxLen)
					 break;					 				 
				 else
					 strRet += c;					 
			 }//for end     
		 }
		 
		 return strRet;
	 }
	 
	 
	 /**
	  * 输入一个字符串，返回一个经过MD5加密后的字符串 
	  * @param strOrigin
	  * @return
	  */
	 public static String MD5Encode(String strOrigin) throws Exception{
		 StringBuffer buf = new StringBuffer();

			    
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  byte[] szBytes = md.digest(strOrigin.getBytes());		  
		  for (int i = 0; i < szBytes.length; i++) {
			  byte b = szBytes[i];
			  int num = b;
			  if(b < 0){
				  num = 256 + b;
			  }		        
			  buf.append(String.valueOf(num));  //使用本函数则返回加密结果的10进制数字字串，即全数字形式
		  }		
	 		
		    
		return buf.toString();
	}
	 
  
	public static int getRandom( int iMax ){
		return (int)(Math.random() * iMax);
	}
	
	
}//class end
