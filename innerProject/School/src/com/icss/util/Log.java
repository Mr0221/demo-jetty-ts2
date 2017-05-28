package com.icss.util;

import org.apache.log4j.Logger;

public class Log {	
	
	static{
		String xx = Log.class.getName();
		System.out.println(xx);
		logger =  Logger.getLogger(Log.class.getName());    //动态获得当前程序的运行位置，并创建日志的文本文件
		System.out.println(Log.class.getResource("/").getPath());    //通过这个方法，可以获得程序运行的位置
	}
    	
	public static Logger logger;       //一次赋值，反复使用
 

}
