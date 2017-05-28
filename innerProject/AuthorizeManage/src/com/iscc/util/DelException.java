package com.iscc.util;

public class DelException extends Exception{

	public DelException(){
		super("请先删除子记录，再删除父记录");
	}
}
