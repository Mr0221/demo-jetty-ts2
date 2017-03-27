package disruptor.ts1;


import java.util.concurrent.Callable;

import com.lmax.disruptor.RingBuffer;

public class GenTrade implements Callable<Void>{
    private final RingBuffer<Trade> ringBuffer;
    public GenTrade(final RingBuffer<Trade> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    public Void call() throws Exception {
         long seq;
         for(int i=0;i<10;i++){
             seq = ringBuffer.next();
             ringBuffer.get(seq).setPrice(Math.random()*9999);
             ringBuffer.publish(seq);
         }
        return null;
    }

}
