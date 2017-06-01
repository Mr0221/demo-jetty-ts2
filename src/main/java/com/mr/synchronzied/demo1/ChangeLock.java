package com.mr.synchronzied.demo1;

/**
 * 锁对象的改变问题
 * @author alienware
 * 可见，虽然将锁改变了，但结果是线程之间是同步的。因为两个线程共同抢占的是同一个锁。
 */
public class ChangeLock {

    private String lock = "lock";

    private void method(){
        synchronized (lock) {
            try {
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(final String[] args) {

        final ChangeLock changeLock = new ChangeLock();
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t1");
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}
