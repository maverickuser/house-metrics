package com.mnnit.housing.internal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnnit.housing.algorithm.PlaceScoreAlgorithm;
import com.mnnit.housing.internal.service.EducationScoreService;
import com.mnnit.housing.model.Place;

@Service
public class EducationScoreServiceImpl implements EducationScoreService {

	@Resource(name = "exponentialDistanceDecayAlgorithm")
    private PlaceScoreAlgorithm algorithm;

    @Value("#{'${google.education.place.types}'.split(',')}")
    private List<String> types;
    
    private double educationWeight=0.1;

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
		return educationWeight*getScore(places);
	}

}
