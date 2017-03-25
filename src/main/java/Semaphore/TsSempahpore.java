package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TsSempahpore {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(5);//5个线程能进入
		for (int index = 0; index < 20; index++) {
			final int no = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						semp.acquire();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("accessing: " + no);
					semp.release();
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}
}
