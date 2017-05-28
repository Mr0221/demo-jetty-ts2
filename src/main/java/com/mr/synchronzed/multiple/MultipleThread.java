package com.mr.synchronzed.multiple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleThread {
	private static int num = 0;
	private static Logger logger = LoggerFactory.getLogger(MultipleThread.class);
	public static synchronized void  printNum(String tag){
		try {
			if(tag.equals("a")){
			num = 100;
			logger.debug("tag a: set value done ");
			
				Thread.sleep(1000);
				
			}else{
				num = 200;
				logger.debug("tag b: set value done ");
			}
			logger.debug("tag " + tag + " : " + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		final MultipleThread m1 =new MultipleThread();
		final MultipleThread m2 = new MultipleThread();
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				m1.printNum("a");
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				m2.printNum("b");
			}
			
		});
		
		t1.start();
		t2.start();
	}

}
