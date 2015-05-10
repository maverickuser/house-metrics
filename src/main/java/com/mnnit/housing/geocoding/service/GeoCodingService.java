package com.mnnit.housing.geocoding.service;

import com.google.maps.model.GeocodingResult;
import com.mnnit.housing.common.MetricsException;
/**
 *  
 * 
 *
 */
public interface GeoCodingService {

	/**
	 * This method is used to get the Geo-Location (Latitude/Longitude) of a Address 
	 * @param apiKey The Google API key
	 * @param address The address for which Geo-Location is needed
	 * @return  GeocodingResult which has details of Geo-Location
	 * @throws MetricsException
	 */
	public GeocodingResult[] getGeoCodingForAddress(String apiKey,String address) throws MetricsException;
	
}
