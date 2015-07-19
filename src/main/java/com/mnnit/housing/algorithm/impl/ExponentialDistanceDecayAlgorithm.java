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
    public Double calculate(List<Place> places) {
    	if(places==null || places.size()==0){
			return 0.0;
		}
    	//calculate mean distance for facilities
    	int i=0;
    	long distance=0;
    	for(Place place:places){
    		if(place!=null){
	    		distance=distance+place.getDistance();
	    		i++;
    		}
    	}
    	double meanDistance=distance/(i*100);
    
    	//calculate the score from exponential decay function
    	double score =100*Math.pow(.90,meanDistance);
    	return score;
    }

}
