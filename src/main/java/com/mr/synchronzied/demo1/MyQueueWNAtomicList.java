package com.mr.synchronzied.demo1;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 模拟Queue
 * @author alienware
 *
 */
public class MyQueueWNAtomicList {
	private LinkedList<Object> list= new LinkedList<Object>();
	private AtomicInteger count = new AtomicInteger(0);
	private final int maxSize;
	private final int minSize = 0;
	private final Object lock = new Object();
	
	public MyQueueWNAtomicList (int maxSize){
		this.maxSize = maxSize;
	}
	
	public void put (Object obj) {
		synchronized(lock){
			if(count.get()==maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			list.add(obj);
			count.incrementAndGet();
			System.out.println("element be add:" + obj);
			lock.notify();
		}
	}
	public Object take(){
		Object temp = null;
		synchronized(lock){
			if(count.get() == minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			temp = list.removeFirst();
			count.decrementAndGet();
			System.out.println("element be remove:" + temp);
			lock.notify();
		}
		return temp;
	}
	public int size(){
		return count.get();
	}
	public static void main(String[] args) throws Exception {

		
		final MyQueueWNAtomicList m = new MyQueueWNAtomicList(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m.put("h");
				m.put("i");
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					Object t1 = m.take();
					//System.out.println("被取走的元素为：" + t1);
					Thread.sleep(1000);
					Object t2 = m.take();
					//System.out.println("被取走的元素为：" + t2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");

		t1.start();
		Thread.sleep(1000);
		t2.start();
		
	}
}
