package com.mr.synchronzied.demo1;

import java.util.concurrent.TimeUnit;

 public class DelayQueue_TimeUinitTest {
     private TimeUnit timeUnit = TimeUnit.DAYS;
 
     public static void main(String[] TimeUinitTest) {
    	 DelayQueue_TimeUinitTest tut = new DelayQueue_TimeUinitTest();
    	 tut.outInfo();
     }
 
     public void outInfo() {
         System.out.println(timeUnit.name());
         System.out.println(timeUnit.toDays(1));
         System.out.println(timeUnit.toHours(1));
         System.out.println(timeUnit.toMinutes(1));
         System.out.println(timeUnit.toSeconds(1));
         System.out.println(timeUnit.toMillis(1));
         System.out.println(timeUnit.toMicros(1));
         System.out.println(timeUnit.toNanos(1));
         System.out.println((timeUnit.convert(1, TimeUnit.DAYS)) + timeUnit.name());
         System.out.println((timeUnit.convert(24, TimeUnit.HOURS)) + timeUnit.name());
         System.out.println((timeUnit.convert(1440, TimeUnit.MINUTES)) + timeUnit.name());
         System.out.println("-------------------");
     }
 }