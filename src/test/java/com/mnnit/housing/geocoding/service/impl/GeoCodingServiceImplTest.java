package com.mnnit.housing.geocoding.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.google.maps.model.GeocodingResult;
import com.mnnit.housing.geocoding.service.GeoCodingService;

public class GeoCodingServiceImplTest {
	private static final String API_KEY = "AIzaSyCN7pa-teQj82uykjO1JcCnTVclF8R7TF4";
	private final GeoCodingService geoCodingService = new GeoCodingServiceImpl();

	@Test
	public void testNull() throws Exception {
		try {
			geoCodingService.getGeoCodingForAddress(null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}
		try {
			geoCodingService.getGeoCodingForAddress("test", null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}

	}

	@Test
	public void testException() throws Exception {
		try {
			geoCodingService.getGeoCodingForAddress("APIkdfmsf",
					"nar vihar 2,sector 34,noida");
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}

		// no result
		GeocodingResult[] results = geoCodingService.getGeoCodingForAddress(
				API_KEY, "viwfnsfdjnsdfkkldfklff");
		Assert.assertEquals(0, results.length);

	}

	@Test
	public void testGeoCoding() throws Exception {
		GeocodingResult[] results = geoCodingService.getGeoCodingForAddress(
				API_KEY, "nar vihar 2 sector 34 noida 201301");
		Assert.assertNotNull(results[0].formattedAddress);
		Assert.assertNotNull(results[0].partialMatch);
		Assert.assertNotNull(results[0].geometry.location);
		System.out.println(results[0].geometry.location);
	}	
}
