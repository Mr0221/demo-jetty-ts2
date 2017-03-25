package com.thread.producer;

//������
class Producer {
 private final Depot depot;

 public Producer(final Depot depot) {
     this.depot = depot;
 }

 // ���Ѳ�Ʒ���½�һ���߳���ֿ���������Ʒ��
 public void produce(final int val) {
     new Thread() {
         @Override
		public void run() {
             depot.produce(val);
         }
     }.start();
 }
}
