package com.tst.log4j.pro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4jTst {

	public static void main(String[] args) {
		Log logger = LogFactory.getLog(Log4jTst.class);
		logger.debug("Debug info.");

        logger.info("Info info");

        logger.warn("Warn info");

        logger.error("Error info");

        logger.fatal("Fatal info");
	}

}
