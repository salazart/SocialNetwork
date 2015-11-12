package com.salazart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2 {
	static final Logger logger = LogManager.getRootLogger();
	public static void main(String[] args) {
		logger.trace("Start==========================");
		logger.trace("Hello this is a trace message");
		logger.debug("Hello this is a debug message");
		logger.info("Hello this is an info message");
		logger.warn("Hello this is an warn message");
		logger.error("Hello this is an error message");
		logger.fatal("Hello this is an fatal message");
		logger.trace("End==========================");
	}

}
