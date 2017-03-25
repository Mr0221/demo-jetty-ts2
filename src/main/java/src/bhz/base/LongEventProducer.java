package src.bhz.base;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
/**
 * 寰堟槑鏄剧殑鏄細褰撶敤涓�釜绠�崟闃熷垪鏉ュ彂甯冧簨浠剁殑鏃跺�浼氱壍娑夋洿澶氱殑缁嗚妭锛岃繖鏄洜涓轰簨浠跺璞¤繕闇�棰勫厛鍒涘缓銆�
 * 鍙戝竷浜嬩欢鏈�皯闇�涓ゆ锛氳幏鍙栦笅涓�釜浜嬩欢妲藉苟鍙戝竷浜嬩欢锛堝彂甯冧簨浠剁殑鏃跺�瑕佷娇鐢╰ry/finnally淇濊瘉浜嬩欢涓�畾浼氳鍙戝竷锛夈�
 * 濡傛灉鎴戜滑浣跨敤RingBuffer.next()鑾峰彇涓�釜浜嬩欢妲斤紝閭ｄ箞涓�畾瑕佸彂甯冨搴旂殑浜嬩欢銆�
 * 濡傛灉涓嶈兘鍙戝竷浜嬩欢锛岄偅涔堝氨浼氬紩璧稤isruptor鐘舵�鐨勬贩涔便�
 * 灏ゅ叾鏄湪澶氫釜浜嬩欢鐢熶骇鑰呯殑鎯呭喌涓嬩細瀵艰嚧浜嬩欢娑堣垂鑰呭け閫燂紝浠庤�涓嶅緱涓嶉噸鍚簲鐢ㄦ墠鑳戒細鎭㈠銆�
 * <B>绯荤粺鍚嶇О锛�/B><BR>
 * <B>妯″潡鍚嶇О锛�/B><BR>
 * <B>涓枃绫诲悕锛�/B><BR>
 * <B>姒傝璇存槑锛�/B><BR>
 * @author 鍖椾含灏氬鍫傦紙alienware锛�
 * @since 2015骞�1鏈�3鏃�
 */
public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;
	
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	/**
	 * onData鐢ㄦ潵鍙戝竷浜嬩欢锛屾瘡璋冪敤涓�灏卞彂甯冧竴娆′簨浠�
	 * 瀹冪殑鍙傛暟浼氱敤杩囦簨浠朵紶閫掔粰娑堣垂鑰�
	 */
	public void onData(ByteBuffer bb){
		//1.鍙互鎶妑ingBuffer鐪嬪仛涓�釜浜嬩欢闃熷垪锛岄偅涔坣ext灏辨槸寰楀埌涓嬮潰涓�釜浜嬩欢妲�
		long sequence = ringBuffer.next();
		try {
			//2.鐢ㄤ笂闈㈢殑绱㈠紩鍙栧嚭涓�釜绌虹殑浜嬩欢鐢ㄤ簬濉厖锛堣幏鍙栬搴忓彿瀵瑰簲鐨勪簨浠跺璞★級
			LongEvent event = ringBuffer.get(sequence);
			//3.鑾峰彇瑕侀�杩囦簨浠朵紶閫掔殑涓氬姟鏁版嵁
			event.setValue(bb.getLong(0));
		} finally {
			//4.鍙戝竷浜嬩欢
			//娉ㄦ剰锛屾渶鍚庣殑 ringBuffer.publish 鏂规硶蹇呴』鍖呭惈鍦�finally 涓互纭繚蹇呴』寰楀埌璋冪敤锛涘鏋滄煇涓姹傜殑 sequence 鏈鎻愪氦锛屽皢浼氬牭濉炲悗缁殑鍙戝竷鎿嶄綔鎴栬�鍏跺畠鐨�producer銆�
			ringBuffer.publish(sequence);
		}
	}
	
	
	
	
	
}
