package com.mr.synchronzied.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectLock {
	private Logger logger = LoggerFactory.getLogger(ObjectLock.class);
	public void method1(){
		synchronized (this){
			logger.debug("method1 doing...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void method2(){
		synchronized (ObjectLock.class){
			logger.debug("method2 doing...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Object object = new Object();
	public void method3(){
		synchronized (object){
			logger.debug("method3 doing...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		final ObjectLock ob = new ObjectLock();
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				ob.method1();
			}
			
		});
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				ob.method2();
			}
			
		});
		Thread t3 = new Thread(new Runnable(){
			public void run() {
				ob.method3();
			}
			
		});
		
		t1.start();
		t2.start();
		t3.start();
	}

}
