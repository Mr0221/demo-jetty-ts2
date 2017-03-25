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
         // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");

        new MyLog4jTst().printLog();

    }

}
