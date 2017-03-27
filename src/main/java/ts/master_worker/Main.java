package ts.master_worker;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(final String[] args) {
         final Logger logger = LoggerFactory.getLogger(Main.class);
        final MWorker workerMoudle = new MWorkerExcutor();
        final MMaster master = new MMaster(workerMoudle , 5);
        for(int i=0; i<100; i++){
            master.submit(i);
        }
        int re= 0;
        logger.debug("master starting.....");
        master.execute();
        final Map<String, Object> resultMap = master.getResultMap();
        while(resultMap.size()>0 ||  !master.isComplete()){

            final Set<String> keys = resultMap.keySet();
            String key =null;
            for(final String k:keys){
                key=k;
                break;
            }
            Integer i =null;
            logger.debug("key value: "+ key);
            if(key!=null){
                i=(Integer)resultMap.get(key);
                logger.debug("calculate: "+i);
            }
            if(i!=null){
                re+=i;
            }
            if(key!=null){
                resultMap.remove(key);
            }

        }
        logger.debug("result: "+Integer.toString(re));
    }
}
