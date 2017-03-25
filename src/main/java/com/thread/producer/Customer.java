package com.thread.producer;

//消费者
class Customer {
 private final Depot depot;

 public Customer(final Depot depot) {
     this.depot = depot;
 }

 // 消费产品：新建一个线程从仓库中消费产品。
 public void consume(final int val) {
     new Thread() {
         @Override
		public void run() {
             depot.consume(val);
         }
     }.start();
 }
}
