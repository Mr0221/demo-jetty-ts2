package disruptor.bhz.base;

import com.lmax.disruptor.EventFactory;
// 闇�璁ヾisruptor涓烘垜浠垱寤轰簨浠讹紝鎴戜滑鍚屾椂杩樺０鏄庝簡涓�釜EventFactory鏉ュ疄渚嬪寲Event瀵硅薄銆�
public class LongEventFactory implements EventFactory {

    public Object newInstance() {
        return new LongEvent();
    }
}
