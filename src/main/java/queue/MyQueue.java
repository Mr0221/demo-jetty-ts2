package queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
  private static final LinkedList<Object> container =  new LinkedList<Object>();
  private final AtomicInteger count = new AtomicInteger(0);
  private final int maxCount;
  private final int minCount = 0;

  private final Object lock = new Object();
  public MyQueue(final int size){
      this.maxCount = size;
  }
  public void put(final Object ob){
      synchronized(lock){
          if(count.get()>=maxCount){
              try {
                  lock.wait();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
          }
          container.add(ob);
          count.incrementAndGet();
          lock.notify();
      }
  }
  public Object get(){
      Object ret = null;
      synchronized(lock){
          if(count.get()<=minCount){
              try {
                  lock.wait();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
          }
          ret = container.removeFirst();
          count.decrementAndGet();
          lock.notify();
      }
      return ret;
  }
}
