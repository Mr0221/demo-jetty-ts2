package com.mr.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinRealLock {
    private final Logger logger = LoggerFactory.getLogger(JoinRealLock.class);

    public synchronized void print(){
        for(int i = 0; i< 1000; i++){
            System.out.println("count i: " + i);
        }
    }
    public static void main(final String[] args) {
        final JoinRealLock jr = new JoinRealLock();
        final Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                jr.print();
            }

        });
        for(int j=0; j<1000; j++){
            if(j==1){
                t1.start();
            }
            if(j==200){
                try {
                    t1.wait();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("count j: " + j);
        }
    }

}
