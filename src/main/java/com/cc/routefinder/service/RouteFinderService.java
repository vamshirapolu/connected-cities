package com.cc.routefinder.service;

/**
 * This class will run the route finding algorithm to find whether there is a
 * route exist from origin to destination. Breadth first search (BFS) algorithm
 * will be used to identify the route existence.
 * 
 * @author Vamshi
 *
 */
public interface RouteFinderService {

	/**
	 * Method to check if the route exists between the origin and the destination.
	 * If is finds the route, it will return "yes" if not it returns "no" as
	 * response.
	 * 
	 * @param origin
	 * @param destination
	 * @return boolean
	 */
	boolean hasRoute(String origin, String destination);

}