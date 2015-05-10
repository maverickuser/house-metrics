package com.mnnit.housing.common;

import org.junit.Test;

public class JavaLoggingLogHandlerTest {

	private static final LogHandler LOG_HANDLER= new JavaLoggingLogHandler(JavaLoggingLogHandlerTest.class);
	
	@Test
	public void testLogging() throws Exception{
		LOG_HANDLER.debug("test");
		LOG_HANDLER.error("test");
		LOG_HANDLER.error("test", new Exception());
		LOG_HANDLER.info("test2");
		LOG_HANDLER.warning("test3");
		LOG_HANDLER.warning("test4",new Exception());
	}
}
