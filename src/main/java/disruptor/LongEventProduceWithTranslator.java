package disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProduceWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;
	
	

	public LongEventProduceWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}



	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSlATR
		= new EventTranslatorOneArg<LongEvent, ByteBuffer>(){

			public void translateTo(LongEvent event, long arg1, ByteBuffer buffer) {
				event.setValue(buffer.getLong(0));
			}
	};



	public void onDate(ByteBuffer byteBuffer) {
		ringBuffer.publishEvent(TRANSlATR, byteBuffer);
	}
	
}
