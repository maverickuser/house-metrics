package com.mnnit.housing.internal.service;

import java.util.List;
import java.util.Map;

import com.mnnit.housing.model.Place;

/**
 * @author manish
 * 
 */
public interface CategoryScoreService {

    Double getScore(Map<PlaceScoreService, List<Place>> graph);

}
