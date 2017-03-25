package disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler {

	public void onEvent(Object longEvent, long l, boolean b) throws Exception {
		System.out.println(((LongEvent)longEvent).getValue());
	}

}
