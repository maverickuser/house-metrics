package com.mnnit.housing.internal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.CategoryScoreAlgorithm;
import com.mnnit.housing.internal.service.CategoryScoreService;
import com.mnnit.housing.internal.service.TransitScoreService;
import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
@Service
public class TransitCategoryScoreServiceImpl implements CategoryScoreService {

    // @Autowired
    private List<TransitScoreService> transitScoreServices;

    // @Autowired
    // This algo is to combine the results of each transit score
    private CategoryScoreAlgorithm algorithm;

    /* (non-Javadoc)
     * @see com.mnnit.housing.internal.service.CategoryScoreService#getScore(java.util.List)
     */
    @Override
    public Long getScore(List<Place> places) {
	Map<TransitScoreService, Long> scoreMap = new HashMap<TransitScoreService, Long>();
	for (TransitScoreService tss : transitScoreServices) {
	    scoreMap.put(tss, tss.getScore(places));
	}

	return algorithm.calculate(scoreMap);
    }

}
