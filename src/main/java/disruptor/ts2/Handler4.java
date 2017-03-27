package disruptor.ts2;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import disruptor.ts1.Trade;

public class Handler4  implements EventHandler<Trade>,WorkHandler<Trade> {

    public void onEvent(final Trade event) throws Exception {
        System.out.println("h4 doing job "+ "id: "+event.getId() + "\t" + event.getName() + "\t" + event.getPrice());

    }

    public void onEvent(final Trade event, final long sequence, final boolean endOfBatch)
            throws Exception {
        this.onEvent(event);
    }

}
