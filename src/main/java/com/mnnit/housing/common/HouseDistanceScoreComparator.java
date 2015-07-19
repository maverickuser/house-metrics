package com.mnnit.housing.common;

import java.util.Comparator;

import com.mnnit.housing.model.Place;

public class HouseDistanceScoreComparator implements Comparator<Place>{

	@Override
	public int compare(Place o1, Place o2) {
		if(o1.getDistance()==o2.getDistance()){
			return 0;
		}
		else if(o1.getDistance()>o2.getDistance()){
			return 1;
		}else {
			return -1;
		}
	}

}
