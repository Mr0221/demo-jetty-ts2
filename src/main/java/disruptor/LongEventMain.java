package disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		LongEventFactory facotry = new LongEventFactory();
		int ringBufferSize = 1024*1024;
		Disruptor<LongEvent> disruptor = 
				new Disruptor<LongEvent>(facotry, ringBufferSize, executor,
						ProducerType.SINGLE, new YieldingWaitStrategy());
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();
		
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
//		LongEventProducer producer = new LongEventProducer(ringBuffer);
		LongEventProduceWithTranslator producer = new LongEventProduceWithTranslator(ringBuffer);
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for(long i = 0; i<100; i++){
			byteBuffer.putLong(0, i);
			producer.onDate(byteBuffer);
		}
		
		disruptor.shutdown();
		executor.shutdown();
	}
}
