package disruptor.bhz.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

    public static void main(final String[] args) throws Exception {
        //鍒涘缓缂撳啿姹�
        final ExecutorService  executor = Executors.newCachedThreadPool();
        //鍒涘缓宸ュ巶
        final LongEventFactory factory = new LongEventFactory();
        //鍒涘缓bufferSize ,涔熷氨鏄疪ingBuffer澶у皬锛屽繀椤绘槸2鐨凬娆℃柟
        final int ringBufferSize = 1024 * 1024; //


        //鍒涘缓disruptor
        final Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        // 杩炴帴娑堣垂浜嬩欢鏂规硶
        disruptor.handleEventsWith(new LongEventHandler());

        // 鍚姩
        disruptor.start();

        //Disruptor 鐨勪簨浠跺彂甯冭繃绋嬫槸涓�釜涓ら樁娈垫彁浜ょ殑杩囩▼锛�
        //鍙戝竷浜嬩欢
        final RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final LongEventProducer producer = new LongEventProducer(ringBuffer);
        //LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        final ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for(long l = 0; l<100; l++){
            byteBuffer.putLong(0, l);
            producer.onData(byteBuffer);
            //Thread.sleep(1000);
        }


        disruptor.shutdown();//鍏抽棴 disruptor锛屾柟娉曚細鍫靛锛岀洿鑷虫墍鏈夌殑浜嬩欢閮藉緱鍒板鐞嗭紱
        executor.shutdown();//鍏抽棴 disruptor 浣跨敤鐨勭嚎绋嬫睜锛涘鏋滈渶瑕佺殑璇濓紝蹇呴』鎵嬪姩鍏抽棴锛�disruptor 鍦�shutdown 鏃朵笉浼氳嚜鍔ㄥ叧闂紱







    }
}
