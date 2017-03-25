package src.bhz.generate1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

public class Main1 {  
   
	public static void main(String[] args) throws Exception {  
        int BUFFER_SIZE=1024;  
        int THREAD_NUMBERS=4;  
        /* 
         * createSingleProducer鍒涘缓涓�釜鍗曠敓浜ц�鐨凴ingBuffer锛�
         * 绗竴涓弬鏁板彨EventFactory锛屼粠鍚嶅瓧涓婄悊瑙ｅ氨鏄�浜嬩欢宸ュ巶"锛屽叾瀹炲畠鐨勮亴璐ｅ氨鏄骇鐢熸暟鎹～鍏匯ingBuffer鐨勫尯鍧椼� 
         * 绗簩涓弬鏁版槸RingBuffer鐨勫ぇ灏忥紝瀹冨繀椤绘槸2鐨勬寚鏁板� 鐩殑鏄负浜嗗皢姹傛ā杩愮畻杞负&杩愮畻鎻愰珮鏁堢巼 
         * 绗笁涓弬鏁版槸RingBuffer鐨勭敓浜ч兘鍦ㄦ病鏈夊彲鐢ㄥ尯鍧楃殑鏃跺�(鍙兘鏄秷璐硅�锛堟垨鑰呰鏄簨浠跺鐞嗗櫒锛�澶參浜�鐨勭瓑寰呯瓥鐣�
         */  
        final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {  
//            @Override  
            public Trade newInstance() {  
                return new Trade();  
            }  
        }, BUFFER_SIZE, new YieldingWaitStrategy());  
        
        //鍒涘缓绾跨▼姹� 
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);  
        
        //鍒涘缓SequenceBarrier  
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();  
          
        //鍒涘缓娑堟伅澶勭悊鍣� 
        BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<Trade>(  
                ringBuffer, sequenceBarrier, new TradeHandler());  
          
        //杩欎竴姝ョ殑鐩殑灏辨槸鎶婃秷璐硅�鐨勪綅缃俊鎭紩鐢ㄦ敞鍏ュ埌鐢熶骇鑰�   濡傛灉鍙湁涓�釜娑堣垂鑰呯殑鎯呭喌鍙互鐪佺暐 
        ringBuffer.addGatingSequences(transProcessor.getSequence());  
          
        //鎶婃秷鎭鐞嗗櫒鎻愪氦鍒扮嚎绋嬫睜  
        executors.submit(transProcessor);  
        
        //濡傛灉瀛樺湪澶氫釜娑堣垂鑰�閭ｉ噸澶嶆墽琛屼笂闈�琛屼唬鐮�鎶奣radeHandler鎹㈡垚鍏跺畠娑堣垂鑰呯被  
          
        Future<?> future= executors.submit(new Callable<Void>() {  
//            @Override  
            public Void call() throws Exception {  
                long seq;  
                for(int i=0;i<10;i++){  
                    seq = ringBuffer.next();//鍗犱釜鍧�--ringBuffer涓�釜鍙敤鍖哄潡  
                    ringBuffer.get(seq).setPrice(Math.random()*9999);//缁欒繖涓尯鍧楁斁鍏�鏁版嵁 
                    ringBuffer.publish(seq);//鍙戝竷杩欎釜鍖哄潡鐨勬暟鎹娇handler(consumer)鍙  
                }  
                return null;  
            }  
        }); 
        
        future.get();//绛夊緟鐢熶骇鑰呯粨鏉� 
        Thread.sleep(1000);//绛変笂1绉掞紝绛夋秷璐归兘澶勭悊瀹屾垚  
        transProcessor.halt();//閫氱煡浜嬩欢(鎴栬�璇存秷鎭�澶勭悊鍣�鍙互缁撴潫浜嗭紙骞朵笉鏄┈涓婄粨鏉�!!锛� 
        executors.shutdown();//缁堟绾跨▼  
    }  
}  