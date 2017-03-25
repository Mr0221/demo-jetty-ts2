package com.log4j.pro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class MyLog4jTst {
    protected Log logger = LogFactory.getLog(getClass());

    public void printLog(){
        logger.debug("this is log factory");
    }
    public static void main(final String[] args) {
        //Log logger = LogFactory.getLog(getClass());
        final Logger logger = Logger.getLogger(MyLog4jTst.class);
         // ��¼debug�������Ϣ
        logger.debug("This is debug message.");
        // ��¼info�������Ϣ
        logger.info("This is info message.");
        // ��¼error�������Ϣ
        logger.error("This is error message.");

        new MyLog4jTst().printLog();

    }

}
