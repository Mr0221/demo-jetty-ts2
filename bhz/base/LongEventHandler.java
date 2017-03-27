package disruptor.bhz.base;

import com.lmax.disruptor.EventHandler;

//鎴戜滑杩橀渶瑕佷竴涓簨浠舵秷璐硅�锛屼篃灏辨槸涓�釜浜嬩欢澶勭悊鍣ㄣ�杩欎釜浜嬩欢澶勭悊鍣ㄧ畝鍗曞湴鎶婁簨浠朵腑瀛樺偍鐨勬暟鎹墦鍗板埌缁堢锛�
public class LongEventHandler implements EventHandler<LongEvent>  {

    public void onEvent(final LongEvent longEvent, final long l, final boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }

}
