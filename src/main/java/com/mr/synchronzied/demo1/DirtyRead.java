package com.mr.synchronzied.demo1;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * @author alienware
 *
 */
public class DirtyRead {

    private String username = "bjsxt";
    private String password = "123";

    public synchronized void setValue(final String username, final String password){
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    public void getValue(){
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }


    public static void main(final String[] args) throws Exception{

        final DirtyRead dr = new DirtyRead();
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);

        dr.getValue();
    }


}
