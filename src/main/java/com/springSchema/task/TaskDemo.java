package com.springSchema.task;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class TaskDemo {
	protected Log logger = LogFactory.getLog(getClass());
	 @Scheduled(cron = "0 0 2 * * ?")
     void doSomethingWith(){
         logger.info("��ʱ����ʼ......");
         long begin = System.currentTimeMillis();
     
         //ִ�����ݿ������Ŷ...
     
         long end = System.currentTimeMillis();
         logger.info("��ʱ�������������ʱ��[" + (end-begin) / 1000 + "]��");
 }
}
