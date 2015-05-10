package com.mnnit.housing.distance.service;

import com.google.maps.model.DistanceMatrix;
import com.mnnit.housing.common.MetricsException;

/**
 * 
 * https://github.com/googlemaps/google-maps-services-java/
 *
 */
public interface DistanceMatrixService {

	/**
	 * 
	 * @param apiKey
	 * @param origins
	 * @param destinations
	 * @throws MetricsException
	 */
	public DistanceMatrix getDistanceMatrix(String apiKey,String[] origins,String[] destinations) throws MetricsException;
}
