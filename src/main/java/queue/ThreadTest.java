package queue;

public class ThreadTest {

    public static void main(final String[] args) {

          final MyQueue que = new MyQueue(1000);

          final Thread t1 = new Thread(new Runnable(){
            public void run() {
                int inter = 0;
                while(true){
                     que.put("put: " + inter++);
                }
            }

          });
          t1.start();
          final Thread t2 = new Thread(new Runnable(){
              public void run() {
                  while(true){
                       System.out.println(que.get());
                  }
              }
            });
          t2.start();
    }
}
