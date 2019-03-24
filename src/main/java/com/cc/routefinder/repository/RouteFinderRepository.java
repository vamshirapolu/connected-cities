package com.cc.routefinder.repository;

import com.cc.routefinder.data.graph.Graph;

/**
 * This class allows to load the city.txt file and build the graph data
 * structure. Once loaded graph data structure will be used to serve repetitive
 * request to find route between the two cities.
 * 
 * @author Vamshi
 *
 */
public interface RouteFinderRepository {

	/**
	 * This method will return the graph data structure object built by loading the
	 * data from city.txt file
	 * 
	 * @return
	 */
	public Graph getCityRoutes();

}