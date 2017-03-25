package src.bhz.generate1;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {  
	  
//    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
//    @Override  
    public void onEvent(Trade event) throws Exception {  
        //杩欓噷鍋氬叿浣撶殑娑堣垂閫昏緫  
        event.setId(UUID.randomUUID().toString());//绠�崟鐢熸垚涓婭D  
        System.out.println(event.getId());  
    }  
}  