package com.mnnit.housing.internal.service;

import java.util.List;

import com.mnnit.housing.model.Place;

/**
 * @author manish
 *
 */
public interface PlaceScoreService {
    
    Long getScore(List<Place> places);

}
