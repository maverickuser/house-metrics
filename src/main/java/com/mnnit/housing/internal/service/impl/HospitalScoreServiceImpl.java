package com.mnnit.housing.internal.service.impl;

import java.util.List;

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

    private PlaceScoreAlgorithm algorithm;

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.PlaceScoreService#getScore(java.util.List)
     */
    @Override
    public Long getScore(List<Place> places) {
	return algorithm.calculate(places);
    }

}
