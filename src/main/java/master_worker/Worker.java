package master_worker;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class Worker extends Thread{
	private ConcurrentLinkedQueue<Task> maskerQueue;
	private ConcurrentMap<String, Object> maskerResultMap;
	
	private String ID;
	
	
	public ConcurrentLinkedQueue<Task> getMaskerQueue() {
		return maskerQueue;
	}
	public void setMaskerQueue(ConcurrentLinkedQueue<Task> maskerQueue) {
		this.maskerQueue = maskerQueue;
	}
	public ConcurrentMap<String, Object> getMaskerResultMap() {
		return maskerResultMap;
	}
	public void setMaskerResultMap(ConcurrentMap<String, Object> maskerResultMap) {
		this.maskerResultMap = maskerResultMap;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	private int price;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public void run() {
		
		super.run();
	}

}
