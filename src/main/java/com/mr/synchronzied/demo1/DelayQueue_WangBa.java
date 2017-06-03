package com.mr.synchronzied.demo1;

import java.util.concurrent.DelayQueue;

public class DelayQueue_WangBa implements Runnable {  
    
    private DelayQueue<DelayQueue_Wangmin> queue = new DelayQueue<DelayQueue_Wangmin>();  
    
    public boolean yinye =true;  
      
    public void shangji(String name,String id,int money){  
        DelayQueue_Wangmin man = new DelayQueue_Wangmin(name, id, 1000 * money + System.currentTimeMillis());  
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块,开始上机...");  
        this.queue.add(man);  
    }  
      
    public void xiaji(DelayQueue_Wangmin man){  
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");  
    }  
  
    @Override  
    public void run() {  
        while(yinye){  
            try {  
                DelayQueue_Wangmin man = queue.take();  
                xiaji(man);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String args[]){  
        try{  
            System.out.println("网吧开始营业");  
            DelayQueue_WangBa siyu = new DelayQueue_WangBa();  
            Thread shangwang = new Thread(siyu);  
            shangwang.start();  
              
            siyu.shangji("路人甲", "123", 1);  
            siyu.shangji("路人乙", "234", 10);  
            siyu.shangji("路人丙", "345", 5);  
        }  
        catch(Exception e){  
            e.printStackTrace();
        }  
  
    }  
}  