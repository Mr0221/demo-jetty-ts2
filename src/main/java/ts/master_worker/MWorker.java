package ts.master_worker;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  abstract class MWorker implements Runnable{
      protected final Logger logger = LoggerFactory.getLogger(MWorker.class);
      private  Queue<Object> ptTaskQueue = new ConcurrentLinkedQueue<Object>();
      private  Map<String, Object> ptResultMap = new ConcurrentHashMap<String, Object>();

      public void run() {
          logger.debug("handel running..");
          while(true){
              final Object task = ptTaskQueue.poll();
              if(task == null){
                  break;
              }
              final Object result = handle(task);
              ptResultMap.put(""+Thread.currentThread().getName(), result);
          }
    }

    public  abstract Object handle(final Object task);

    public Queue<Object> getPtTaskQueue() {
        return ptTaskQueue;
    }

    public void setPtTaskQueue(final Queue<Object> ptTaskQueue) {
        this.ptTaskQueue = ptTaskQueue;
    }

    public Map<String, Object> getPtResultMap() {
        return ptResultMap;
    }

    public void setPtResultMap(final Map<String, Object> ptResultMap) {
        this.ptResultMap = ptResultMap;
    }

}
