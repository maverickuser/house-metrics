package com.mnnit.housing.places.service;

import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.places.PlacesResult;

public interface PlacesService {

	/**
	 * This service is used to get the List of Point of Interest (food
	 * joints,atm,doctor,market) from @param latLon in a circle of @param radius 
	 * @param apiKey
	 * @param latLon The location from which POI are to be identified (Center of circle)
	 * @param radius The distance from the centre for whihc POi need to be identified
	 * @param types type of POI
	 * @return PlacesResult which has list of places and their details
	 * @throws MetricsException
	 */
	public PlacesResult getNearPlaces(String apiKey, String latLon,
			String radius, String[] types) throws MetricsException;
}
