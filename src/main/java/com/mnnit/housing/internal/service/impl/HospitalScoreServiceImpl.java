package com.mnnit.housing.internal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.HealthScoreService;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service
public class HospitalScoreServiceImpl implements HealthScoreService {

    @Resource(name = "meanDistanceScoreAlgorithm")
    private PlaceScoreAlgorithm algorithm;

    @Value("#{'${google.hospital.place.types}'.split(',')}")
    private List<String> types;

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.PlaceScoreService#getScore(java.util.List)
     */
    @Override
    public Double getScore(List<Place> places) {
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

	@Override
	public Double getWeightedScore(List<Place> places) {
		// TODO Auto-generated method stub
		return null;
	}

}
