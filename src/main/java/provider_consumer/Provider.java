package provider_consumer;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Provider implements Runnable{
	public static Logger logger = LoggerFactory.getLogger("provider_consumer.Provider");
	private BlockingQueue <Data> queue;
	private volatile boolean isRunning = true;
	private static AtomicInteger count = new AtomicInteger();
	private static Random r = new Random();
	public Provider(BlockingQueue queue){
		this.queue = queue;
	}
	public void run() {
		while(isRunning){
			int id = count.incrementAndGet();
			Data data = new Data(Integer.toString(id), "Êý¾Ý"+ id);
			try {
				if(!this.queue.offer(data, 2, TimeUnit.SECONDS)){
					logger.debug("commit buffer data fail...");
				}
			} catch (InterruptedException e) {
				logger.debug(e.toString());
				e.printStackTrace();
			}
		}
	}
	public void stop(){
		this.isRunning = false;
	}
}
