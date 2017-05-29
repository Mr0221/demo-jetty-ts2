package com.mr.synchronzied.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncDubbo2 {
	private static Logger logger = LoggerFactory.getLogger(SyncDubbo2.class);
	static class Main{
		public int i = 10;
		public synchronized void operationSup(){
			try {
				i --;
				logger.debug("Main thread: " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	static class Sub extends Main{
		
		public synchronized void operationSup(){
			try {
				while(i>0){
					i --;
					logger.debug("SUb thread: " + i);
					Thread.sleep(100);
					super.operationSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				Sub s1 = new Sub();
				s1.operationSup();
			}
			
		});
		t1.start();
	}

}
