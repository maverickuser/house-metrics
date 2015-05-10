package com.mnnit.housing.distance.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.mnnit.housing.common.MetricsException;

public class DistanceMatrixServiceImplTest {
	private static final String API_KEY = "AIzaSyCN7pa-teQj82uykjO1JcCnTVclF8R7TF4";
	DistanceMatrixServiceImpl distanceMatrixServiceImpl = new DistanceMatrixServiceImpl();

	@Test
	public void testNull() throws Exception {
		try {
			distanceMatrixServiceImpl.getDistanceMatrix(null, null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}
		try {
			distanceMatrixServiceImpl.getDistanceMatrix(API_KEY, null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}
		try {
			distanceMatrixServiceImpl.getDistanceMatrix(API_KEY,
					new String[] {}, null);
		} catch (Exception e) {
			Assert.assertNotNull(e.getCause().toString());
		}

	}

	@Test
	public void testDistanceMatrix() throws Exception {
		String[] origins = { "37.422546,-122.084250" };
		String[] destinations = { "37.422146, -122.084785",
				"37.422363, -122.08423", "37.42214, -122.08478" };
		DistanceMatrix matrix = distanceMatrixServiceImpl.getDistanceMatrix(
				API_KEY, origins, destinations);
		DistanceMatrixRow[] rows = matrix.rows;
		for (DistanceMatrixRow row : rows) {
			DistanceMatrixElement[] elements = row.elements;
			for (DistanceMatrixElement element : elements) {
				Assert.assertEquals("OK",element.status.toString());
				Assert.assertNotNull(element.distance.humanReadable);
				Assert.assertNotNull(element.distance.inMeters);
				Assert.assertNotNull(element.duration.inSeconds);
				Assert.assertNotNull(element.duration.humanReadable);
			}
		}
	}

	@Test
	public void testException() throws Exception {
		try {
			distanceMatrixServiceImpl.getDistanceMatrix("sfdjsdkfsdkf",
					new String[] {}, new String[] {});
		} catch (MetricsException me) {
			Assert.assertNotNull(me.getCause());
		}
		try {
		distanceMatrixServiceImpl.getDistanceMatrix(API_KEY,
				new String[] {}, new String[] {});
		}catch(MetricsException me){
			Assert.assertNotNull(me.getCause());
		}
		
	}
}
