package com.mnnit.housing.places.service.impl;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mnnit.housing.common.BaseTest;
import com.mnnit.housing.model.Place;
import com.mnnit.housing.places.service.ScoreService;

/**
 * @author manish
 *
 */
public class ScoreServiceTest extends BaseTest {

	@Autowired
	private ScoreService scoreService;
	@Autowired
	private PlacesServiceImpl placesService;

	@Test
	public void testGetScore() throws Exception {
		System.out.println(scoreService
				.getScore("nar vihar 2,sector 34,noida"));
				/*String coordinates = scoreService
				.getCoordinates("nar vihar 2,sector 34,noida");
		System.out.println(coordinates);
		Set<Place> result = scoreService.getNearbyPlaces(coordinates);
		System.out.println(result.size());
		// get all the types
		/*String[] types = { "establishment", "store", "Food",
				"grocery_or_supermarket", "department_store",
				"convenience_store", "meal_delivery", "meal_takeaway",
				"bakery", "cafe", "restaurant", "school", "university",
				"doctor", "hospital", "pharmacy", "health", "plumber",
				"electrician", "hindu_temple", "hair_care", "beauty_salon",
				" bank", "atm", "amusement_park", "aquarium", "art_gallery",
				"library", "book_store", "bowling_alley", "movie_theater",
				"stadium" };
		int p=0;
		for (int i = 100; i <= 300; i = i + 50) {
			p=p+placesService.getNearPlaces(coordinates, types, "" + i).size();
		}
		System.out.println(p);*/
	}

}
