package ts.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MMaster {
    private final Queue<Object> taskQueue = new ConcurrentLinkedQueue<Object>();
    private final HashMap<String , Thread> workerMap = new HashMap<String , Thread>();
    private final Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

    public MMaster(final MWorker workerMoudle, final int num){
        for(int i=0; i<num; i++	){
            workerMoudle.setPtTaskQueue(taskQueue);
            workerMoudle.setPtResultMap(resultMap);
            workerMap.put(Integer.toString(i), new Thread(workerMoudle, Integer.toString(i)));
        }
    }

    public boolean isComplete(){
        for(final Map.Entry<String, Thread> worker : workerMap.entrySet()){
            if(worker.getValue().getState() != Thread.State.TERMINATED ){
                return false;
            }
        }
        return true;
    }
    public  void submit(final Object task){
        taskQueue.add(task);
    }
    public void execute(){
        for(final Map.Entry<String, Thread>worker : workerMap.entrySet()){
            worker.getValue().start();
        }
    }
    public Map<String, Object> getResultMap (){
        return resultMap;
    }
}
