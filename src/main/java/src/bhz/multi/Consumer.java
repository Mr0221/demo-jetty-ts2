package src.bhz.multi;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<Order>{
	
	private String consumerId;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	public Consumer(String consumerId){
		this.consumerId = consumerId;
	}

//	@Override
	public void onEvent(Order order) throws Exception {
		System.out.println("褰撳墠娑堣垂鑰� " + this.consumerId + "锛屾秷璐逛俊鎭細" + order.getId());
		count.incrementAndGet();
	}
	
	public int getCount(){
		return count.get();
	}

}
