package com.icss.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.security.*;

public class SysUtility {
		 
	/**
	 * �ַ��������ʾʱ��һ������ռ����λ�ã�һ��Ӣ�Ļ�����ռһ��λ��
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
	  *  �ж�ĳ���ַ��Ƿ�Ϊ�����ַ� 
	  * @param c
	  * @return
	  */
	 public static boolean IsChineseChar(char c){
		 boolean bRet;
		 
		 //ASCII��ķ�Χ��0~255
		 if(c>255 || c<0){
			 bRet = true;
		 }
		 else
		 	bRet = false;
		 
		 return bRet;	
	 }
	 
	 
	 /**
	  * ���ָ�����ȵ��ַ��� --iMaxLenΪ�����ȡ��Ӣ���ַ�����--һ��������ռ����Ӣ���ֳ��� 
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
	  * ����һ���ַ���������һ������MD5���ܺ���ַ��� 
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
			  buf.append(String.valueOf(num));  //ʹ�ñ������򷵻ؼ��ܽ����10���������ִ�����ȫ������ʽ
		  }		
	 		
		    
		return buf.toString();
	}
	 
  
	public static int getRandom( int iMax ){
		return (int)(Math.random() * iMax);
	}
	
	
}//class end
