package com.mr.synchronzied.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncException {
	private int i = 1;
	private Logger logger = LoggerFactory.getLogger(SyncException.class);
	public synchronized void operation(){
		while(true){
			i++;
			logger.debug("i value = " + i);
			try {
				Thread.sleep(100);
				if(i==10){
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("error :: i value = " + i);
				continue;// continue to run
				//throw new RunntimeException(e) //stop running
			}
		}
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				new SyncException().operation();;
			}
			
		});
		
		t1.start();
	}

}
