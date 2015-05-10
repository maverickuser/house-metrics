package com.mnnit.housing.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaLoggingLogHandler implements LogHandler {

	/** logger instance */
	private Logger LOGGER = null;

	/** constructor */
	public JavaLoggingLogHandler(final Class<?> klass) {
		LOGGER = Logger.getLogger(klass.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	public void error(String message, Exception exception) {
		LOGGER.log(Level.SEVERE, message, exception);
	}

	/**
	 * {@inheritDoc}
	 */
	public void error(String message) {
		LOGGER.severe(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public void warning(String message, Exception exception) {
		LOGGER.log(Level.WARNING, message, exception);
	}

	/**
	 * {@inheritDoc}
	 */
	public void warning(String message) {
		LOGGER.warning(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public void info(String message) {
		LOGGER.info(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public void debug(String message) {
		LOGGER.log(Level.FINE, message);
	}
}
