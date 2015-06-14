package com.mnnit.housing.places.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.model.GeocodingResult;
import com.mnnit.housing.algorithm.TopScoreAlgorithm;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.distance.service.DistanceMatrixService;
import com.mnnit.housing.geocoding.service.GeoCodingService;
import com.mnnit.housing.internal.service.CategoryScoreService;
import com.mnnit.housing.model.Place;
import com.mnnit.housing.model.PlacesResult;
import com.mnnit.housing.places.service.PlacesService;
import com.mnnit.housing.places.service.ScoreService;

/**
 * @author manish
 *
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private GeoCodingService geoCodingService;

    @Autowired
    private PlacesService placesService;

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @Autowired
    private List<CategoryScoreService> categoryScoreServices;

    @Autowired
    private TopScoreAlgorithm scoreAlgorithm;

    @Value(value = "${google.map.apikey}")
    private String apiKey;

    /* (non-Javadoc)
     * @see com.mnnit.housing.places.service.ScoreService#getScore(java.lang.String)
     */
    @Override
    public Long getScore(String address) {
	String coordinates = getCoordinates(address);
	PlacesResult result = getNearbyPlaces(coordinates);

	result = distanceMatrixService.updatePlacesWithDistance(result,
		coordinates);

	return null;
    }

    private String getCoordinates(String address) {
	String coordinates = "";
	try {
	    GeocodingResult results[] = geoCodingService
		    .getGeoCodingForAddress(apiKey, address);
	    coordinates = results[0].geometry.location.toString();
	} catch (MetricsException e) {
	    throw new RuntimeException(
		    "Failed to find coordinates of address: " + address, e);
	}
	return coordinates;
    }

    private PlacesResult getNearbyPlaces(String coordinates) {
	try {
	    return placesService.getNearPlaces(coordinates, new String[1]);
	} catch (MetricsException e) {
	    throw new RuntimeException(
		    "Failed to find nearby places for coordinates:"
			    + coordinates, e);
	}
    }

    private Long getScore(List<Place> places) {
	Long score = 0L;
	for (Place place : places) {

	}

	return score;
    }

}
