package src.bhz.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

	public static void main(String[] args) throws Exception {
		//鍒涘缓缂撳啿姹�
		ExecutorService  executor = Executors.newCachedThreadPool();
		//鍒涘缓宸ュ巶
		LongEventFactory factory = new LongEventFactory();
		//鍒涘缓bufferSize ,涔熷氨鏄疪ingBuffer澶у皬锛屽繀椤绘槸2鐨凬娆℃柟
		int ringBufferSize = 1024 * 1024; // 

		/**
		//BlockingWaitStrategy 鏄渶浣庢晥鐨勭瓥鐣ワ紝浣嗗叾瀵笴PU鐨勬秷鑰楁渶灏忓苟涓斿湪鍚勭涓嶅悓閮ㄧ讲鐜涓兘鎻愪緵鏇村姞涓�嚧鐨勬�鑳借〃鐜�
		WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		//SleepingWaitStrategy 鐨勬�鑳借〃鐜拌窡BlockingWaitStrategy宸笉澶氾紝瀵笴PU鐨勬秷鑰椾篃绫讳技锛屼絾鍏跺鐢熶骇鑰呯嚎绋嬬殑褰卞搷鏈�皬锛岄�鍚堢敤浜庡紓姝ユ棩蹇楃被浼肩殑鍦烘櫙
		WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		//YieldingWaitStrategy 鐨勬�鑳芥槸鏈�ソ鐨勶紝閫傚悎鐢ㄤ簬浣庡欢杩熺殑绯荤粺銆傚湪瑕佹眰鏋侀珮鎬ц兘涓斾簨浠跺鐞嗙嚎鏁板皬浜嶤PU閫昏緫鏍稿績鏁扮殑鍦烘櫙涓紝鎺ㄨ崘浣跨敤姝ょ瓥鐣ワ紱渚嬪锛孋PU寮�惎瓒呯嚎绋嬬殑鐗规�
		WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		*/
		
		//鍒涘缓disruptor
		Disruptor<LongEvent> disruptor = 
				new Disruptor<LongEvent>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		// 杩炴帴娑堣垂浜嬩欢鏂规硶
		disruptor.handleEventsWith(new LongEventHandler());
		
		// 鍚姩
		disruptor.start();
		
		//Disruptor 鐨勪簨浠跺彂甯冭繃绋嬫槸涓�釜涓ら樁娈垫彁浜ょ殑杩囩▼锛�
		//鍙戝竷浜嬩欢
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer); 
		//LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for(long l = 0; l<100; l++){
			byteBuffer.putLong(0, l);
			producer.onData(byteBuffer);
			//Thread.sleep(1000);
		}

		
		disruptor.shutdown();//鍏抽棴 disruptor锛屾柟娉曚細鍫靛锛岀洿鑷虫墍鏈夌殑浜嬩欢閮藉緱鍒板鐞嗭紱
		executor.shutdown();//鍏抽棴 disruptor 浣跨敤鐨勭嚎绋嬫睜锛涘鏋滈渶瑕佺殑璇濓紝蹇呴』鎵嬪姩鍏抽棴锛�disruptor 鍦�shutdown 鏃朵笉浼氳嚜鍔ㄥ叧闂紱		
		
		
		
		
		
		
		
	}
}
