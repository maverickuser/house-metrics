package com.mnnit.housing.algorithm.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service(value = "exponentialDistanceDecayAlgorithm")
public class ExponentialDistanceDecayAlgorithm implements PlaceScoreAlgorithm {

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.ScoreAlgorithm#calculate(java.util.List)
     */
    @Override
    public Long calculate(List<Place> places) {
	// TODO Auto-generated method stub
	return null;
    }

}
