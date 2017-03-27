package disruptor.ts1;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {

    public void onEvent(final disruptor.ts1.Trade event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        System.out.println(event.getId() +"\t" + event.getPrice());

    }

    public void onEvent(final disruptor.ts1.Trade event, final long sequence,
            final boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

}
