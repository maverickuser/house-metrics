package com.mnnit.housing.algorithm;

import java.util.List;

import com.mnnit.housing.model.Place;

/**
 * This is interface for all place scoring algorithms. <br>
 * Each place service can have a different algorithm for scoring.<br>
 * 
 * @author manish
 * 
 */
public interface PlaceScoreAlgorithm {

    Long calculate(List<Place> places);

}
