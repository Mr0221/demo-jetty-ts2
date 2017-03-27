package disruptor.ts2;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import disruptor.ts1.Trade;

public class Main {

    public static void main(final String[] args) {
         final int bufferSize=1024;
        final ExecutorService executor=Executors.newFixedThreadPool(8);
        final Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {
            public Trade newInstance() {
                return new Trade();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        final Handler1 h1 = new Handler1();
        final Handler2 h2 = new Handler2();
        final Handler3 h3 = new Handler3();
        final Handler4 h4 = new Handler4();
        final Handler5 h5 = new Handler5();
        disruptor.handleEventsWith(h1, h2);
        disruptor.after(h1).handleEventsWith(h3);
        disruptor.after(h2).handleEventsWith(h4);
        disruptor.after(h3, h4).handleEventsWith(h5);

        disruptor.start();
        final CountDownLatch latch=new CountDownLatch(1);
        executor.execute(new TradePublisher(latch, disruptor));
        try {
            latch.await();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        disruptor.shutdown();
        executor.shutdown();
    }

}
