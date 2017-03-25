package master_worker;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap; 
public class Master {
	private ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<Task>(); 
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();
	private ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	public Master(Worker worker, int acount){
		Random rm = new Random();
		for(int i=0; i<acount; i++){
			worker.setMaskerQueue(this.queue);
			worker.setMaskerResultMap(this.resultMap);
			worker.setID("WorkerId_"+i);
			worker.setPrice(rm.nextInt(1000));
			workers.put("Worker"+i, worker);
		}
	}
}
