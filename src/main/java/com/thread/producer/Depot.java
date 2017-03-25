package com.thread.producer;

//�ֿ�
class Depot {
 private final int capacity;    // �ֿ������
 private int size;        // �ֿ��ʵ������

 public Depot(final int capacity) {
     this.capacity = capacity;
     this.size = 0;
 }

 public synchronized void produce(final int val) {
     try {
          // left ��ʾ����Ҫ������������(�п���������̫�࣬��������)
         int left = val;
         while (left > 0) {
             // �������ʱ���ȴ��������ߡ����Ѳ�Ʒ��
             while (size >= capacity) {
				wait();
			}
             // ��ȡ��ʵ��������������(�����������������)
             // �������桱+����Ҫ������������>���ܵ�����������ʵ��������=���ܵ�������-����ǰ��������(��ʱ�����ֿ�)
             // ����ʵ��������=����Ҫ������������
             final int inc = (size+left)>capacity ? (capacity-size) : left;
             size += inc;
             left -= inc;
             System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                     Thread.currentThread().getName(), val, left, inc, size);
             // ֪ͨ�������ߡ����������ˡ�
             notifyAll();
         }
     } catch (final InterruptedException e) {
     }
 }

 public synchronized void consume(final int val) {
     try {
         // left ��ʾ���ͻ�Ҫ����������(�п���������̫�󣬿�治������������)
         int left = val;
         while (left > 0) {
             // ���Ϊ0ʱ���ȴ��������ߡ�������Ʒ��
             while (size <= 0) {
				wait();
			}
             // ��ȡ��ʵ�����ѵ�������(�������ʵ�ʼ��ٵ�����)
             // �������桱<���ͻ�Ҫ���ѵ�����������ʵ����������=����桱��
             // ���򣬡�ʵ����������=���ͻ�Ҫ���ѵ���������
             final int dec = (size<left) ? size : left;
             size -= dec;
             left -= dec;
             System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                     Thread.currentThread().getName(), val, left, dec, size);
             notifyAll();
         }
     } catch (final InterruptedException e) {
     }
 }

 @Override
public String toString() {
     return "capacity:"+capacity+", actual size:"+size;
 }
}
