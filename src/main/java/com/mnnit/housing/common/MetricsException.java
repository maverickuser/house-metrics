package com.mnnit.housing.common;

/**
 * MetricsException and it's descendants represent an error returned by the
 * remote API.
 */
public class MetricsException extends Exception {
	private static final long serialVersionUID = -6550606366694345191L;

	public MetricsException(String message, Throwable t) {
		super(message, t);
	}
}
