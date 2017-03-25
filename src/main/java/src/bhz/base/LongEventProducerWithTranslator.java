package src.bhz.base;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * Disruptor 3.0鎻愪緵浜唋ambda寮忕殑API銆傝繖鏍峰彲浠ユ妸涓�簺澶嶆潅鐨勬搷浣滄斁鍦≧ing Buffer锛�
 * 鎵�互鍦―isruptor3.0浠ュ悗鐨勭増鏈渶濂戒娇鐢‥vent Publisher鎴栬�Event Translator鏉ュ彂甯冧簨浠�
 * <B>绯荤粺鍚嶇О锛�/B><BR>
 * <B>妯″潡鍚嶇О锛�/B><BR>
 * <B>涓枃绫诲悕锛�/B><BR>
 * <B>姒傝璇存槑锛�/B><BR>
 * @author 鍖椾含灏氬鍫傦紙alienware锛�
 * @since 2015骞�1鏈�3鏃�
 */
public class LongEventProducerWithTranslator {

	//涓�釜translator鍙互鐪嬪仛涓�釜浜嬩欢鍒濆鍖栧櫒锛宲ublicEvent鏂规硶浼氳皟鐢ㄥ畠
	//濉厖Event
	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = 
			new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
				public void translateTo(LongEvent event, long sequeue, ByteBuffer buffer) {
					event.setValue(buffer.getLong(0));
				}
			};
	
	private final RingBuffer<LongEvent> ringBuffer;
	
	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer buffer){
		ringBuffer.publishEvent(TRANSLATOR, buffer);
	}
	
	
	
}
