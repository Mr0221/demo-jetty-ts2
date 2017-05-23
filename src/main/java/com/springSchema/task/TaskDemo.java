package com.springSchema.task;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class TaskDemo {
	protected Log logger = LogFactory.getLog(getClass());
	 @Scheduled(cron = "0 0 2 * * ?")
     void doSomethingWith(){
         logger.info("定时任务开始......");
         long begin = System.currentTimeMillis();
     
         //执行数据库操作了哦...
     
         long end = System.currentTimeMillis();
         logger.info("定时任务结束，共耗时：[" + (end-begin) / 1000 + "]秒");
 }
}
