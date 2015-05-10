package com.mnnit.housing.distance.service.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.mnnit.housing.common.JavaLoggingLogHandler;
import com.mnnit.housing.common.LogHandler;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.distance.service.DistanceMatrixService;

/**
 * 
 * 
 *
 */
public class DistanceMatrixServiceImpl implements DistanceMatrixService {
	private static final LogHandler logHandler = new JavaLoggingLogHandler(
			DistanceMatrixServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public DistanceMatrix getDistanceMatrix(String apiKey, String[] origins,
			String[] destinations) throws MetricsException {
		if (apiKey == null || origins == null || destinations == null) {
			throw new IllegalArgumentException(
					new NullPointerException(
							"Arguments 'apikey' or 'origins' or 'destinations' is null"));
		}
		// https://github.com/googlemaps/google-maps-services-java/blob/master/src/main/java/com/google/maps/DistanceMatrixApi.java
		DistanceMatrix distanceMatrix = null;
		try {
			GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
			//test case for https://developers.google.com/maps/documentation/distancematrix/#StatusCodes
			distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context,
					origins, destinations).await();

		} catch (Exception e) {
			logHandler.error("Exception in getDistanceMatrix");
			throw new MetricsException("Exception in getDistanceMatrix", e);
		}

		return distanceMatrix;
		// multiple setting
		// https://github.com/googlemaps/google-maps-services-java/blob/master/src/test/java/com/google/maps/DistanceMatrixApiIntegrationTest.java

	}
}
