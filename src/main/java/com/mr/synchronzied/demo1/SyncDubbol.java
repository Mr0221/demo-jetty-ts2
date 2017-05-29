package com.mr.synchronzied.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SyncDubbol {
	private Logger logger =  LoggerFactory.getLogger(SyncDubbol.class);
	public synchronized void mothod1(){
		logger.debug("m1 running...");
		mothod2();
	}
	
	public synchronized void mothod2(){
		logger.debug("m2 running...");
		mothod3();
	}
	public synchronized void mothod3(){
		logger.debug("m3 running...");
	}
	public static void main(String[] args) {
		final SyncDubbol s1 = new SyncDubbol();
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				s1.mothod1();
			}
			
		});
		t1.start();
	}

}
