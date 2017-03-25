package com.thread.producer;

//������
class Customer {
 private final Depot depot;

 public Customer(final Depot depot) {
     this.depot = depot;
 }

 // ���Ѳ�Ʒ���½�һ���̴߳Ӳֿ������Ѳ�Ʒ��
 public void consume(final int val) {
     new Thread() {
         @Override
		public void run() {
             depot.consume(val);
         }
     }.start();
 }
}
