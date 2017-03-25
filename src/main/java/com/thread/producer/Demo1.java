package com.thread.producer;

public class Demo1 {
    public static void main(final String[] args) {
        final Depot mDepot = new Depot(100);
        final Producer mPro = new Producer(mDepot);
        final Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
