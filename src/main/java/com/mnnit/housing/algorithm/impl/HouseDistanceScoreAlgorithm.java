package com.mnnit.housing.algorithm.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.common.HouseDistanceScoreComparator;
import com.mnnit.housing.model.Place;

@Service(value = "houseDistanceScoreAlgorithm")
public class HouseDistanceScoreAlgorithm implements PlaceScoreAlgorithm {

	@Override
	public Double calculate(List<Place> places) {
		//sort the list of place by distance
		Collections.sort(places, new HouseDistanceScoreComparator());
		//take the min distance
		double minDistance=places.get(0).getDistance();
		//get score
		double score =100*Math.pow(.90,(minDistance/100));
		int j=0;
		for(int i=1;i<places.size();i++){
			if((places.get(i).getDistance()-minDistance)<=100){
				j++;
			}
		}
		if(j!=0){
			//add boost
			score=score*Math.log1p(1+j);
		}
		return score;
	}


}
