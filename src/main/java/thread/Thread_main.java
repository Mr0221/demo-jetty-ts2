package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


class Temp extends Thread{
	public void run(){
		System.out.println("run");
	}
}
public class Thread_main {
 public static void main(String[] args) {
//	ExecutorService pool = Executors.newCachedThreadPool();
	 Temp command = new Temp();
	 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	 scheduler.scheduleWithFixedDelay(command, 1,  3, TimeUnit.SECONDS);
 }
}
