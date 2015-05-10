package com.mnnit.housing.common;
/**
 * 
 * Base housing Metrics logging wrapper
 *
 */
public interface LogHandler {

	/** log a error message */
	void error(String message, Exception exception);

	/** log a error message */
	void error(String message);
	
	/** log a warning message */
	void warning(String message, Exception exception);

	/** log a warning message */
	void warning(String message);

	/** log a debug message */
	void info(String message);

	/** log a debug message */
	void debug(String message);
}
