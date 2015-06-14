package com.mnnit.housing.distance.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.mnnit.housing.common.JavaLoggingLogHandler;
import com.mnnit.housing.common.LogHandler;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.distance.service.DistanceMatrixService;
import com.mnnit.housing.model.Place;
import com.mnnit.housing.model.PlacesResult;

@Service
public class DistanceMatrixServiceImpl implements DistanceMatrixService {
    private static final LogHandler logHandler = new JavaLoggingLogHandler(
	    DistanceMatrixServiceImpl.class);

    @Value(value = "${google.map.apikey}")
    private String apiKey;

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
	    // test case for
	    // https://developers.google.com/maps/documentation/distancematrix/#StatusCodes
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mnnit.housing.geocoding.service.GeoCodingService#updatePlacesWithDistance
     * (com.mnnit.housing.model.PlacesResult)
     */
    @Override
    public PlacesResult updatePlacesWithDistance(PlacesResult placesResult,
	    String coordinates) {
	String[] origins = { coordinates };
	String[] destinations = new String[placesResult.asList().size()];
	int i = 0;
	for (Place place : placesResult.asList()) {
	    destinations[i++] = place.getGeometry().getLocation().toString();
	}
	DistanceMatrix matrix = null;
	try {
	    matrix = getDistanceMatrix(apiKey, origins, destinations);
	} catch (MetricsException e) {
	    throw new RuntimeException(
		    "Failed to update distance for nearby places.", e);
	}
	i = 0;
	for (DistanceMatrixElement dest : matrix.rows[0].elements) {
	    placesResult.asList().get(i).setDistance(dest.distance.inMeters);
	}

	return placesResult;
    }
}
