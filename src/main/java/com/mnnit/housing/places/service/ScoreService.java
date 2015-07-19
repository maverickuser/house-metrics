package com.mnnit.housing.places.service;

import java.util.Set;

import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
public interface ScoreService {

    Double getScore(String address);
    Set<Place> getNearbyPlaces(String address);
    String getCoordinates(String address);

}
