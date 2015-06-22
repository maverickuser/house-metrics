package com.mnnit.housing.internal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.TransitScoreService;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service
public class TrainStationScoreServiceImpl implements TransitScoreService {

    @Resource(name = "meanDistanceScoreAlgorithm")
    private PlaceScoreAlgorithm algorithm;

    @Value("#{'${google.trainStation.place.types}'.split(',')}")
    private List<String> types;

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.PlaceScoreService#getScore(java.util.List)
     */
    @Override
    public Long getScore(List<Place> places) {
	// Iterate over the list and get only Airports.
	// pass the new airport list to the algorithm to get the score.

	return algorithm.calculate(places);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.mnnit.housing.internal.service.PlaceScoreService#getTypes()
     */
    @Override
    public List<String> getTypes() {
	return types;
    }

}
