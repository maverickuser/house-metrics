package com.mnnit.housing.algorithm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.CategoryScoreAlgorithm;
import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.PlaceScoreService;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service(value = "meanDistanceScoreAlgorithm")
public class MeanDistanceScoreAlgorithm implements CategoryScoreAlgorithm,
	PlaceScoreAlgorithm {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mnnit.housing.algorithm.PlaceScoreAlgorithm#calculate(java.util.List)
     */
    @Override
    public Double calculate(List<Place> places) {
	for (Place place : places) {
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mnnit.housing.algorithm.CategoryScoreAlgorithm#calculate(java.util
     * .Map)
     */
    @Override
    public Double calculate(Map<? extends PlaceScoreService, Long> scoresMap) {
	return null;
    }

}
