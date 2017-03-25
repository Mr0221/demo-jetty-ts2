package disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onDate(ByteBuffer byteBuffer) {
		long sequence = ringBuffer.next();
		try{
			LongEvent event = ringBuffer.get(sequence);
			event.setValue(byteBuffer.getLong(0));
		}finally{
			ringBuffer.publish(sequence);
		}
	}

}
