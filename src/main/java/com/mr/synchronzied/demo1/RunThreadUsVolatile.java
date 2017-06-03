package com.mr.synchronzied.demo1;

public class RunThreadUsVolatile extends Thread{

	private volatile boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThreadUsVolatile rt = new RunThreadUsVolatile();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置了false");
	}
	
	
}
