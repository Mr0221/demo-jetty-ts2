package com.thread.producer;

//生产者
class Producer {
 private final Depot depot;

 public Producer(final Depot depot) {
     this.depot = depot;
 }

 // 消费产品：新建一个线程向仓库中生产产品。
 public void produce(final int val) {
     new Thread() {
         @Override
		public void run() {
             depot.produce(val);
         }
     }.start();
 }
}
