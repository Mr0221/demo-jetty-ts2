package com.mr.synchronzied.demo1;

public class ThreadLocalUs {

	public static ThreadLocal<String> th = new ThreadLocal<String>();
	
	public void setTh(String value){
		th.set(value);
	}
	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final ThreadLocalUs ct = new ThreadLocalUs();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("张三");
				ct.getTh();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.setTh("李四");
					ct.getTh();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
}
