package com.mr.synchronzied.demo1;

public class SingletonDubble {

	private static SingletonDubble ds;
	
	public static SingletonDubble getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (SingletonDubble.class) {
				if(ds == null){
					ds = new SingletonDubble();
				}
			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(SingletonDubble.getDs().hashCode());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(SingletonDubble.getDs().hashCode());
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(SingletonDubble.getDs().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
