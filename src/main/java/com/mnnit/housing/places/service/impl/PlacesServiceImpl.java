package com.mnnit.housing.places.service.impl;

import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mnnit.housing.common.JavaLoggingLogHandler;
import com.mnnit.housing.common.LogHandler;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.model.PlacesResult;
import com.mnnit.housing.places.service.PlacesService;

/**
 * 
 * This class is implementation PlacesService interface.
 */
@Service
public class PlacesServiceImpl implements PlacesService {

    private static final LogHandler logHandler = new JavaLoggingLogHandler(
	    PlacesServiceImpl.class);
    private static final String NEARBY_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    @Value(value = "${google.map.apikey}")
    private String apiKey = "";

    /**
     * The distance from the center for which POi need to be identified
     */
    @Value(value = "${radius.of.interest}")
    private String radius = "";

    /**
     * {@inheritDoc}
     */
    public PlacesResult getNearPlaces(String latLon, String[] types)
	    throws MetricsException {
	if (apiKey == null || latLon == null || radius == null) {
	    throw new IllegalArgumentException(new NullPointerException(
		    "Arguments apiKey or 'latLon' or 'radius' is null"));
	}
	// Reference implementation from
	// 'https://github.com/claygregory/google-places-api-java'
	PlacesResult placesResult = null;
	try {
	    URIBuilder url = new URIBuilder(NEARBY_SEARCH_URL);
	    url.addParameter("key", apiKey);
	    url.addParameter("location", latLon);
	    url.addParameter("radius", radius);
	    if (types != null) {
		url.addParameter("types", getTypes(types));
	    }
	    HttpGet get = new HttpGet(url.build());
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse response = client.execute(get);
	    // returning the first page result...
	    // need to handle multiple page ..will need to check
	    // placesResult.getNextPageToken()
	    // Will return status
	    placesResult = new Gson().fromJson(new InputStreamReader(response
		    .getEntity().getContent()), PlacesResult.class);

	} catch (Exception e) {
	    logHandler.error("Exception in getting getNearPlaces .. "
		    + e.getMessage());
	    throw new MetricsException("Exception in getting getNearPlaces", e);
	}
	return placesResult;
    }

    /**
     * Method to create a string of type as '|' separated list
     * 
     * @param types
     *            as defined in
     *            https://developers.google.com/places/supported_types
     * @return a concatenated string of types separated by '|'
     */
    private String getTypes(String[] types) {
	StringBuilder typesString = new StringBuilder();
	for (String type : types) {
	    if (typesString.length() != 0)
		typesString.append('|');
	    typesString.append(type);
	}

	return typesString.toString();
    }

}
