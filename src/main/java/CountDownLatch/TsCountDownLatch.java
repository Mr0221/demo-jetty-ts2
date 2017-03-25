package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class TsCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch countdown = new CountDownLatch(2);// 2 for call countdown.countdown()

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t1 enter.. , waiting...");
				try {
					countdown.await();
					System.out.println("t1 continue..");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// t2
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t2 enter.. , waiting...");
				countdown.countDown();
				System.out.println("t2 continue..");
			}
		});

		// t3
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				System.out.println("t3 enter.. , waiting...");
				countdown.countDown();
				System.out.println("t3 continue..");
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
	}

}
