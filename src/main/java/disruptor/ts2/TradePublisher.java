package disruptor.ts2;


import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.dsl.Disruptor;

import disruptor.ts1.Trade;


public class TradePublisher implements Runnable{

    Disruptor<Trade> disruptor;
    private final CountDownLatch latch;

    private static int LOOP=10;

    public TradePublisher(final CountDownLatch latch,final Disruptor<Trade> disruptor) {
        this.disruptor=disruptor;
        this.latch=latch;
    }
    public void run() {
        final TradeEventTranslator tradeTransloator = new TradeEventTranslator();
        for(int i=0;i<LOOP;i++){
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }

}
