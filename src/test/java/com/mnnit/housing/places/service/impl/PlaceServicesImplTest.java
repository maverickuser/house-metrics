package com.mnnit.housing.places.service.impl;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.mnnit.housing.places.Place;
import com.mnnit.housing.places.PlacesResult;

public class PlaceServicesImplTest {
	private static final String API_KEY = "AIzaSyCN7pa-teQj82uykjO1JcCnTVclF8R7TF4";
	private final PlacesServiceImpl placesServiceImpl = new PlacesServiceImpl();

	@Test
	public void testNull() throws Exception {
		try {
			placesServiceImpl.getNearPlaces(null, null, null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}
		try {
			placesServiceImpl.getNearPlaces("test", null, null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}
		try {
			placesServiceImpl.getNearPlaces("test", "test", null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetNearPlaces() throws Exception {
		String latlong = "37.422546,-122.084250";
		String radius = "50";
		//types supported ---https://developers.google.com/places/supported_types
		String[] types = { "atm", "grocery_or_supermarket", "food", "doctor" };
		PlacesResult placesResult = placesServiceImpl.getNearPlaces(API_KEY,
				latlong, radius, types);
		Assert.assertEquals("OK", placesResult.getStatus());
		Assert.assertEquals(true, placesResult.isOkay());
		Assert.assertNotNull(placesResult.size());
		Assert.assertNotNull(placesResult.asList().toString());
		Assert.assertNull(placesResult.getNextPageToken());
		Iterator<Place> iterator = placesResult.iterator();
		while (iterator.hasNext()) {
			Place place = iterator.next();
			Assert.assertNotNull(place.getName());
			place.getRating();
			place.getTypes().toString();
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
		}

		PlacesResult placesResult1 = placesServiceImpl.getNearPlaces(API_KEY,
				latlong, radius, null);
		Assert.assertNotNull(placesResult1);
	}

	@Test
	public void testException() throws Exception {
		// wrong latitude..longitude or wrong radius i.e. 0,-1
		String latlong = "dd,dsfsdf";
		String radius = "50";
		String[] types = { "atm", "grocery_or_supermarket", "food", "doctor" };
		PlacesResult placesResult = placesServiceImpl.getNearPlaces(API_KEY,
				latlong, radius, types);
		Assert.assertEquals("INVALID_REQUEST",placesResult.getStatus());
		Assert.assertEquals(false,placesResult.isOkay());
		latlong = "37.422546,-122.084250";
		// wrong api key
		PlacesResult placesResult1 = placesServiceImpl.getNearPlaces(
				"suhdsdaasiud", latlong, radius, types);
		Assert.assertEquals("REQUEST_DENIED",placesResult1.getStatus());
		Assert.assertEquals(false,placesResult1.isOkay());
		// types as null
		PlacesResult placesResult2 = placesServiceImpl.getNearPlaces(
				API_KEY, latlong, radius, new String[]{null});
		Assert.assertEquals("ZERO_RESULTS",placesResult2.getStatus());
		Assert.assertEquals(false,placesResult2.isOkay());
		
	}
}
