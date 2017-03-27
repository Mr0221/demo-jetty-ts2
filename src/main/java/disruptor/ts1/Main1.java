package disruptor.ts1;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

public class Main1 {
      public static void main(final String[] args) throws Exception {
          final int BUFFER_SIZE=1024;
          final int THREAD_NUMBERS=4;
          final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new TradeFactory(), BUFFER_SIZE, new YieldingWaitStrategy());
          final ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

          final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
          final BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<Trade>(
                  ringBuffer, sequenceBarrier, new TradeHandler());
          ringBuffer.addGatingSequences(transProcessor.getSequence());

          executors.submit(transProcessor);
          final Future<?> future = executors.submit(new GenTrade(ringBuffer));
          future.get();
          Thread.sleep(1000);
          transProcessor.halt();
          executors.shutdown();
      }
}
