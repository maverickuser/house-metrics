package com.mnnit.housing.places.service;

import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.model.PlacesResult;

public interface PlacesService {

	/**
	 * This service is used to get the List of Point of Interest (food
	 * joints,atm,doctor,market) from @param latLon in a circle of @param radius 
	 * @param latLon The location from which POI are to be identified (Center of circle)
	 * @param types type of POI
	 * @return PlacesResult which has list of places and their details
	 * @throws MetricsException
	 */
    public PlacesResult getNearPlaces(String latLon, String[] types)
	    throws MetricsException;
}
