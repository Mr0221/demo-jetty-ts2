package src.bhz.multi;

import java.nio.ByteBuffer;
import java.util.UUID;

import src.bhz.base.LongEvent;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * <B>绯荤粺鍚嶇О锛�/B><BR>
 * <B>妯″潡鍚嶇О锛�/B><BR>
 * <B>涓枃绫诲悕锛�/B><BR>
 * <B>姒傝璇存槑锛�/B><BR>
 * @author 鍖椾含灏氬鍫傦紙alienware锛�
 * @since 2015骞�1鏈�3鏃�
 */
public class Producer {

	private final RingBuffer<Order> ringBuffer;
	
	public Producer(RingBuffer<Order> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	/**
	 * onData鐢ㄦ潵鍙戝竷浜嬩欢锛屾瘡璋冪敤涓�灏卞彂甯冧竴娆′簨浠�
	 * 瀹冪殑鍙傛暟浼氱敤杩囦簨浠朵紶閫掔粰娑堣垂鑰�
	 */
	public void onData(String data){
		//鍙互鎶妑ingBuffer鐪嬪仛涓�釜浜嬩欢闃熷垪锛岄偅涔坣ext灏辨槸寰楀埌涓嬮潰涓�釜浜嬩欢妲�
		long sequence = ringBuffer.next();
		try {
			//鐢ㄤ笂闈㈢殑绱㈠紩鍙栧嚭涓�釜绌虹殑浜嬩欢鐢ㄤ簬濉厖锛堣幏鍙栬搴忓彿瀵瑰簲鐨勪簨浠跺璞★級
			Order order = ringBuffer.get(sequence);
			//鑾峰彇瑕侀�杩囦簨浠朵紶閫掔殑涓氬姟鏁版嵁
			order.setId(data);
		} finally {
			//鍙戝竷浜嬩欢
			//娉ㄦ剰锛屾渶鍚庣殑 ringBuffer.publish 鏂规硶蹇呴』鍖呭惈鍦�finally 涓互纭繚蹇呴』寰楀埌璋冪敤锛涘鏋滄煇涓姹傜殑 sequence 鏈鎻愪氦锛屽皢浼氬牭濉炲悗缁殑鍙戝竷鎿嶄綔鎴栬�鍏跺畠鐨�producer銆�
			ringBuffer.publish(sequence);
		}
	}
	
	
}
