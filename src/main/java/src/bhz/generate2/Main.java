package src.bhz.generate2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import src.bhz.generate1.Trade;
import src.bhz.generate1.TradeHandler;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {  
    public static void main(String[] args) throws InterruptedException {  
       
    	long beginTime=System.currentTimeMillis();  
        int bufferSize=1024;  
        ExecutorService executor=Executors.newFixedThreadPool(8);  

        Disruptor<Trade> disruptor = new Disruptor<Trade>(new EventFactory<Trade>() {  
//            @Override  
            public Trade newInstance() {  
                return new Trade();  
            }  
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());  
        
        //鑿卞舰鎿嶄綔
        /**
        //浣跨敤disruptor鍒涘缓娑堣垂鑰呯粍C1,C2  
        EventHandlerGroup<Trade> handlerGroup = 
        		disruptor.handleEventsWith(new Handler1(), new Handler2());
        //澹版槑鍦–1,C2瀹屼簨涔嬪悗鎵цJMS娑堟伅鍙戦�鎿嶄綔 涔熷氨鏄祦绋嬭蛋鍒癈3 
        handlerGroup.then(new Handler3());
        */
        
        //椤哄簭鎿嶄綔
        /**
        disruptor.handleEventsWith(new Handler1()).
        	handleEventsWith(new Handler2()).
        	handleEventsWith(new Handler3());
        */
        
        //鍏竟褰㈡搷浣� 
        /**
        Handler1 h1 = new Handler1();
        Handler2 h2 = new Handler2();
        Handler3 h3 = new Handler3();
        Handler4 h4 = new Handler4();
        Handler5 h5 = new Handler5();
        disruptor.handleEventsWith(h1, h2);
        disruptor.after(h1).handleEventsWith(h4);
        disruptor.after(h2).handleEventsWith(h5);
        disruptor.after(h4, h5).handleEventsWith(h3);
        */
        
        
        
        disruptor.start();//鍚姩  
        CountDownLatch latch=new CountDownLatch(1);  
        //鐢熶骇鑰呭噯澶� 
        executor.submit(new TradePublisher(latch, disruptor));
        
        latch.await();//绛夊緟鐢熶骇鑰呭畬浜� 
       
        disruptor.shutdown();  
        executor.shutdown();  
        System.out.println("鎬昏�鏃�"+(System.currentTimeMillis()-beginTime));  
    }  
}  