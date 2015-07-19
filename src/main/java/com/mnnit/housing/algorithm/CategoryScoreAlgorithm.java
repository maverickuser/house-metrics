package com.mnnit.housing.algorithm;

import java.util.Map;

import com.mnnit.housing.internal.service.PlaceScoreService;

/**
 * This is interface for all category scoring algorithms. <br>
 * Each category service can have a different algorithm for scoring.<br>
 * 
 * @author manish
 * 
 */
public interface CategoryScoreAlgorithm {

    Double calculate(Map<? extends PlaceScoreService, Long> scoresMap);

}
