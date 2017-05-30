package com.mr.synchronzied.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringDeadLock {
	private Logger logger = LoggerFactory.getLogger(StringDeadLock.class);
	
	public void method(){
		//new String("") //can stop this happen
		synchronized("String"){
			try {
				while(true){
				logger.debug("beging doing..."+ Thread.currentThread().getName());
				Thread.sleep(1000);
				logger.debug("end doing..."+ Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		final StringDeadLock ob = new StringDeadLock();
		Thread t1 = new Thread (new Runnable(){

			public void run() {
				ob.method();
			}
			
		});
		Thread t2 = new Thread (new Runnable(){

			public void run() {
				ob.method();
			}
			
		});
		t1.start();
		t2.start();
	}

}
