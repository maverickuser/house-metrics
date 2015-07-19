package com.mnnit.housing.internal.service;

import java.util.List;

import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
public interface PlaceScoreService {
    
    /**
     * Get the score for the list of places.
     * 
     * @param places
     * @return
     */
    Double getScore(List<Place> places);

    /**
     * Get the types of the places supported by the service.
     * 
     * @return
     */
    List<String> getTypes();
    
    /**
     * Get the weighted score for the 
     * @param places
     * @return
     */
    Double getWeightedScore(List<Place> places);
}
