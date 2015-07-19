package com.mnnit.housing.internal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.RetailScoreService;
import com.mnnit.housing.model.Place;

@Service
public class RetailScoreServiceImpl implements RetailScoreService {

	@Resource(name = "houseDistanceScoreAlgorithm")
    private PlaceScoreAlgorithm algorithm;

    @Value("#{'${google.retail.place.types}'.split(',')}")
    private List<String> types;
    
    private double retailweight=0.4;
    
	@Override
	public Double getScore(List<Place> places) {
		return algorithm.calculate(places);
	}

	@Override
	public List<String> getTypes() {
		return types;
	}

	@Override
	public Double getWeightedScore(List<Place> places) {		
		return retailweight*getScore(places);
	}

}
