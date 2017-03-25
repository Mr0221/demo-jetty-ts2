package furturmodel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String para;
	public UseFuture(String para){
		this.para = para;
	}
	public static void main(String[] args) {
		String queryStr = "query";
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future returnValue= executor.submit(future);
		while(true){
			try {
				if(returnValue.get() == null){
					System.out.println("futurn task is finish..");
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("send asking..");
		try {
			System.out.println("return: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
	public String call() throws Exception {
		String result = this.para + " job done";
		return result;
	}

}
