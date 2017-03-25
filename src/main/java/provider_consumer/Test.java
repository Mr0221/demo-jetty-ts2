package provider_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	public static void main(String[] args) {
		BlockingQueue<Data> queue = new LinkedBlockingQueue<Data> (10);
		Provider p1 = new Provider(queue);
		Provider p2 = new Provider(queue);
		Provider p3 = new Provider(queue);
		
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		
		 ExecutorService cachePool = Executors.newCachedThreadPool();
		 cachePool.execute(p1);
		 cachePool.execute(p2);
		 cachePool.execute(p3);
		 cachePool.execute(c1);
		 cachePool.execute(c2);
		 cachePool.execute(c3);
		 
	}
}
