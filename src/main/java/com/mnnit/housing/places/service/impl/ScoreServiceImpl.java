package com.mnnit.housing.places.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.mnnit.housing.common.MetricsException;
import com.mnnit.housing.distance.service.DistanceMatrixService;
import com.mnnit.housing.geocoding.service.GeoCodingService;
import com.mnnit.housing.internal.service.CategoryScoreService;
import com.mnnit.housing.internal.service.PlaceScoreService;
import com.mnnit.housing.model.Place;
import com.mnnit.housing.places.service.PlacesService;
import com.mnnit.housing.places.service.ScoreService;

/**
 * @author manish
 *
 */
@Service
public class ScoreServiceImpl implements ScoreService {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private GeoCodingService geoCodingService;

	@Autowired
	private PlacesService placesService;

	@Autowired
	private DistanceMatrixService distanceMatrixService;

	@Autowired
	private List<CategoryScoreService> categoryScoreServices;

	@Autowired
	private List<PlaceScoreService> placeScoreServices;

	/*
	 * @Autowired private TopScoreAlgorithm scoreAlgorithm;
	 */

	@Value(value = "${google.map.apikey}")
	private String apiKey;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mnnit.housing.places.service.ScoreService#getScore(java.lang.String)
	 */
	@Override
	public Double getScore(String address) {
		Double walkScore=0d;
		// get the geo location for the address
		String coordinates = getCoordinates(address);
		// get places
		Set<Place> results = getNearbyPlaces(coordinates);
		//update the places set with distance
		updatePlaceWithDistance(coordinates,results);
		
		Iterator<Place> resultItr = results.iterator();
		while(resultItr.hasNext()){
			Place p=resultItr.next();
			System.out.println("Place Name " + p.getName() +" Place Type" +p.getTypes().toString() +"Place distance" + p.getDistance() );
		}
		//Map the place object to its corresponding PlaceServiceScore Implementation
		Map<PlaceScoreService, List<Place>> servicePlaceMap = prepareGraph(results);
		//get the score
		Set<Map.Entry<PlaceScoreService, List<Place>>> servicePlaceSet = servicePlaceMap.entrySet();
		Iterator<Entry<PlaceScoreService, List<Place>>> itr = servicePlaceSet.iterator();
	        while (itr.hasNext()) {
	            Entry<PlaceScoreService,  List<Place>> entryDetail = itr.next();
	            System.out.println("class -->" + entryDetail.getKey().getClass().getName()  +" score is "+ entryDetail.getKey().getWeightedScore(entryDetail.getValue()));
	            walkScore=walkScore+entryDetail.getKey().getWeightedScore(entryDetail.getValue());
	        }
		
		/*if (!result.isOkay()) {
			throw new RuntimeException("Failed to get nearby places "
					+ result.getStatus());
		}

		result = distanceMatrixService.updatePlacesWithDistance(result,
				coordinates);
		Map<PlaceScoreService, List<Place>> servicePlaceMap = prepareGraph(result
				.asList());
		Map<CategoryScoreService, Long> catCcoresMap = new HashMap<CategoryScoreService, Long>();
		for (CategoryScoreService cService : categoryScoreServices) {
			catCcoresMap.put(cService, cService.getScore(servicePlaceMap));
		}*/

		//return scoreAlgorithm.calculate(catCcoresMap);
		return walkScore;
	}

	public String getCoordinates(String address) {
		System.out.println("sizeeee" + placeScoreServices.size());
		String coordinates = "";
		try {
			GeocodingResult results[] = geoCodingService
					.getGeoCodingForAddress(apiKey, address);
			coordinates = results[0].geometry.location.toString();
		} catch (MetricsException e) {
			throw new RuntimeException(
					"Failed to find coordinates of address: " + address, e);
		}
		return coordinates;
	}

	public Set<Place> getNearbyPlaces(String coordinates) {
		try {
			// get all the types
			String[] types = { "store", "Food", "grocery_or_supermarket",
					"department_store", "convenience_store", "meal_delivery",
					"meal_takeaway", "bakery", "cafe", "restaurant", "school",
					"university", "doctor", "hospital", "pharmacy", "health",
					"plumber", "electrician", "hindu_temple", "hair_care",
					"beauty_salon", " bank", "atm", "amusement_park",
					"aquarium", "art_gallery", "library", "book_store",
					"bowling_alley", "movie_theater", "stadium" };
			// loop through radius from 100-300 with 50m gap
			Set<Place> results = Collections.newSetFromMap(new LinkedHashMap<Place, Boolean>(16, 0.75f, true));;
			for (int i = 100; i <= 300; i = i + 50) {
				addUniquePlaces(
						placesService.getNearPlaces(coordinates, types, "" + i)
								.asList(), results);
			}

			return results;
		} catch (MetricsException e) {
			throw new RuntimeException(
					"Failed to find nearby places for coordinates:"
							+ coordinates, e);
		}
	}

	private Map<PlaceScoreService, List<Place>> prepareGraph(
			Collection<Place> places) {
		Map<PlaceScoreService, List<Place>> graph = new HashMap<>();
		for (Place place : places) {
			for (PlaceScoreService placeScoreService : placeScoreServices) {
				if (!CollectionUtils.intersection(placeScoreService.getTypes(),
						place.getTypes()).isEmpty()) {
					if (graph.get(placeScoreService) == null) {
						graph.put(placeScoreService, new ArrayList<Place>());
					}
					graph.get(placeScoreService).add(place);
				}
			}
		}
		return graph;
	}

	private void addUniquePlaces(List<Place> placeList, Set<Place> results) {
		Iterator<Place> itr = placeList.iterator();
		while (itr.hasNext()) {
			results.add(itr.next());
		}
	}

	private void updatePlaceWithDistance(String addressCoordinates,
			Set<Place> results) {
		String[] origins = { addressCoordinates };
		String[] destinations = new String[results.size()];
		int i = 0;
		for (Place place : results) {
			destinations[i++] = place.getGeometry().getLocation().toString();
		}
		DistanceMatrix matrix = null;
		try {
			matrix = distanceMatrixService.getDistanceMatrix(apiKey, origins,
					destinations);
		} catch (MetricsException e) {
			throw new RuntimeException(
					"Failed to update distance for nearby places.", e);
		}
		// as only one origin
		DistanceMatrixElement[] matrixElements = matrix.rows[0].elements;
		// assumption is the order of destinations array is same as the irder of
		// distance in DistanceMatrixElement
		Map<String, Long> distanceMap = new HashMap<String, Long>();
		for (int p = 0; p < matrixElements.length; p++) {
			distanceMap.put(destinations[p],
					matrixElements[p].distance.inMeters);
		}

		// update results with the distance
		for (Place place : results) {
			place.setDistance(distanceMap.get(place.getGeometry().getLocation()
					.toString()));
		}
	}
}
