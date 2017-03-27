package disruptor.ts2;

import java.util.Random;
import java.util.UUID;

import com.lmax.disruptor.EventTranslator;

import disruptor.ts1.Trade;

public class TradeEventTranslator implements EventTranslator<Trade>{
    private final Random random=new Random();
    public void translateTo(final Trade event, final long sequence) {
        event.setId(UUID.randomUUID().toString());
        event.setName("shop"+ random.nextInt()*10);
        event.setPrice(random.nextDouble()*9999);
    }

}
