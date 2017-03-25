package provider_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	private BlockingQueue<Data> queue;
	public Consumer(BlockingQueue queue){
		this.queue = queue;
	}
	private static Random r = new Random();
	public void run() {
		while(true){
			try {
				Data data = this.queue.take();
				System.out.println("current thread: " + Thread.currentThread().getName() +
						"send successk, data key is: " + data.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
}
