package com.mnnit.housing.internal.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.CategoryScoreAlgorithm;
import com.mnnit.housing.internal.service.CategoryScoreService;
import com.mnnit.housing.internal.service.PlaceScoreService;
import com.mnnit.housing.internal.service.TransitScoreService;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service
public class TransitCategoryScoreServiceImpl implements CategoryScoreService {

    @Autowired
    private List<TransitScoreService> transitScoreServices;

    @Resource(name = "meanDistanceScoreAlgorithm")
    // This algo is to combine the results of each transit score
    private CategoryScoreAlgorithm algorithm;

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.CategoryScoreService#getScore(java.util.List)
     */
    @Override
    public Double getScore(Map<PlaceScoreService, List<Place>> graph) {
	/*Map<TransitScoreService, Double> scoreMap = new HashMap<TransitScoreService, Double>();
	for (TransitScoreService tss : transitScoreServices) {
	    scoreMap.put(tss, tss.getScore(graph.get(tss)));
	}

	return algorithm.calculate(scoreMap);
*/   
    	return null;
    	}

}
