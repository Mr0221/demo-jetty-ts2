package demp;

import java.util.concurrent.atomic.AtomicInteger;

public class A {
    static Point currentPos = new Point(new AtomicInteger(1), new AtomicInteger(2));
    static class Point{
         AtomicInteger x;
         AtomicInteger y;
        public Point(final AtomicInteger x,final AtomicInteger y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(final String[] args) {

        new Thread(){
            void f(final Point p){
                synchronized (this) {
                    if(p.x.get()+1 != p.y.get()){
                             System.out.println(p.x.get()+" "+p.y.get());
                             System.exit(1);
                    }
                }
            }
            @Override
            public void run() {
                while(currentPos == null) {
                    ;
                }
                while(true) {
//                    System.out.println("run ..0");
                    f(currentPos);
                }
            }
        }.start();
        int x=0;
        int y=0;
        while(true){
             x= currentPos.x.addAndGet(1);
            y= currentPos.y.addAndGet(1);
            currentPos = new Point(new AtomicInteger(x), new AtomicInteger(y));
        }
    }
}
