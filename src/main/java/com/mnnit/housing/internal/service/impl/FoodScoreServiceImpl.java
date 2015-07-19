package com.mnnit.housing.internal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.FoodScoreService;
import com.mnnit.housing.model.Place;
@Service
public class FoodScoreServiceImpl implements FoodScoreService {

	@Resource(name = "exponentialDistanceDecayAlgorithm")
    private PlaceScoreAlgorithm algorithm;

    @Value("#{'${google.food.place.types}'.split(',')}")
    private List<String> types;
    
    private double foodWeight=0.15;
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
		return foodWeight*getScore(places);
	}

}
