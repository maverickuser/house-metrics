package com.mnnit.housing.places;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * POJO for setting  Places API response JSON object.
 * This class maps to the JSON object returned by the Places API
 *
 */
public class PlacesResult extends Result implements Iterable<Place> {
	
	private String nextPageToken;

	private List<Place> results;
		
	public List<Place> asList( ) {
		return Collections.unmodifiableList( this.results );
	}

	public String getNextPageToken( ) {
		return this.nextPageToken;
	}
	
	public Iterator<Place> iterator() {
		return this.results.iterator();
	}

	public int size( ) {
		return this.results.size( );
	}

}
