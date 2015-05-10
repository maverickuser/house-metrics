package com.mnnit.housing.geocoding.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.mnnit.housing.common.JavaLoggingLogHandler;
import com.mnnit.housing.common.LogHandler;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.geocoding.service.GeoCodingService;

/**
 * 
 * This service is implementation of Google Geo-Coding service
 *  https://github.com/googlemaps/google-maps-services-java/
 *
 */
public class GeoCodingServiceImpl implements GeoCodingService {

	private static final LogHandler logHandler = new JavaLoggingLogHandler(
			GeoCodingServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public GeocodingResult[] getGeoCodingForAddress(String apiKey,
			String address) throws MetricsException {
		if (apiKey == null || address == null) {
			throw new IllegalArgumentException(new NullPointerException(
					"Arguments 'apikey' or 'address' is null"));
		}

		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		GeocodingResult[] results = null;
		try {
			// synchronous request hence wait for the response.
			// can add timeout in GeoApiContext or implement a call back for
			// asynchronous
			results = GeocodingApi.geocode(context, address).await();
		} catch (Exception e) {
			logHandler.error("Exception in getGeoCodingForAddress");
			throw new MetricsException("Exception in getGeoCodingForAddress", e);
		}
		return results;
	}

}
