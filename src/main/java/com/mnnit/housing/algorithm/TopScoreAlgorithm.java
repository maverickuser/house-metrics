package com.mnnit.housing.algorithm;

import java.util.Map;

import com.mnnit.housing.internal.service.CategoryScoreService;

/**
 * This is interface for top level scoring algorithms. <br>
 * This will aggregate the score for all different categories <br>
 * 
 * @author manish
 * 
 */
public interface TopScoreAlgorithm {

    Long calculate(Map<CategoryScoreService, Long> scoresMap);

}
