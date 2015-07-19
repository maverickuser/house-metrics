package com.mnnit.housing.places.service.impl;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mnnit.housing.common.BaseTest;
import com.mnnit.housing.model.Place;
import com.mnnit.housing.model.PlacesResult;

public class PlaceServicesImplTest extends BaseTest {

    @Value(value = "${google.map.apikey}")
    private String API_KEY;

    @Autowired
    private PlacesServiceImpl placesService;

    private String radius = "200";

    @Test
    public void testNull() throws Exception {
	try {
	    placesService.getNearPlaces(null, null,null);
	} catch (Exception e) {
	    Assert.assertNotNull(e.getCause().toString());
	}
	try {
	    placesService.getNearPlaces(null, null,null);
	} catch (Exception e) {
	    Assert.assertNotNull(e.getCause().toString());
	}
	try {
	    placesService.getNearPlaces("test", null,null);
	} catch (Exception e) {
	    Assert.assertNotNull(e.getCause().toString());
	}

    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetNearPlaces() throws Exception {
	String latlong = "28.585790,77.359899";
	// types supported
	// ---https://developers.google.com/places/supported_types
	String[] types ={ "store", "Food",
			"grocery_or_supermarket", "department_store",
			"convenience_store", "meal_delivery", "meal_takeaway",
			"bakery", "cafe", "restaurant", "school", "university",
			"doctor", "hospital", "pharmacy", "health", "plumber",
			"electrician", "hindu_temple", "hair_care", "beauty_salon",
			" bank", "atm", "amusement_park", "aquarium",
			"art_gallery", "library", "book_store", "bowling_alley",
			"movie_theater", "stadium" };
	PlacesResult placesResult = placesService.getNearPlaces(latlong,
		types,"300");
	System.out.println("token-->"+placesResult.getNextPageToken());
	System.out.println("result size-->"+placesResult.size());
	Assert.assertEquals("OK", placesResult.getStatus());
	Assert.assertEquals(true, placesResult.isOkay());
	Assert.assertNotNull(placesResult.size());
	Assert.assertNotNull(placesResult.asList().toString());
	Assert.assertNull(placesResult.getNextPageToken());
	Iterator<Place> iterator = placesResult.iterator();
	while (iterator.hasNext()) {
	    Place place = iterator.next();
	    Assert.assertNotNull(place.getName());
	    System.out.println(place.getName());
	    place.getRating();
	    place.getTypes().toString();
	    System.out.println(place.getTypes().toString());
	    place.getFormattedAddress();
	    place.getIcon();
	    place.getId();
	    place.getPlaceId();
	    place.getUrl();
	    place.getVicinity();
	    place.getReference();
	    Assert.assertNotNull(place.getGeometry().getLocation());
	    Assert.assertNotNull(place.getGeometry().toString());
	    System.out.println(place.getGeometry().getLocation());
	    System.out.println("##########################");
	}

	PlacesResult placesResult1 = placesService.getNearPlaces(latlong,
		null,radius);
	Assert.assertNotNull(placesResult1);
    }

    @Test
    public void testException() throws Exception {
	// wrong latitude..longitude or wrong radius i.e. 0,-1
	String latlong = "dd,dsfsdf";

	String[] types = { "atm", "grocery_or_supermarket", "food", "doctor" };
	PlacesResult placesResult = placesService.getNearPlaces(latlong,
		types, radius);
	Assert.assertEquals("INVALID_REQUEST", placesResult.getStatus());
	Assert.assertEquals(false, placesResult.isOkay());
	latlong = "37.422546,-122.084250";
	// types as null
	PlacesResult placesResult2 = placesService.getNearPlaces(latlong,
		new String[] { null }, radius);
	Assert.assertEquals("ZERO_RESULTS", placesResult2.getStatus());
	Assert.assertEquals(false, placesResult2.isOkay());

    }
}
