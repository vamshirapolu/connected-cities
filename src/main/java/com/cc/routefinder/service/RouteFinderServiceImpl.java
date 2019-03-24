package com.cc.routefinder.service;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.routefinder.data.graph.Graph;
import com.cc.routefinder.data.graph.Vertex;
import com.cc.routefinder.repository.RouteFinderRepository;

/**
 * This class will run the route finding algorithm to find whether there is a
 * route exist from origin to destination. Breadth first search (BFS) algorithm
 * will be used to identify the route existence.
 * 
 * @author Vamshi
 *
 */
@Service
public class RouteFinderServiceImpl implements RouteFinderService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private RouteFinderRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cc.routefinder.service.RouteFinderService#hasRoute(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean hasRoute(String origin, String destination) {
		if (origin.equals(destination)) {
			return false;
		} else {
			Graph graph = repository.getCityRoutes();
			Vertex originVertex = graph.getVertex(origin);
			if (originVertex == null) {
				return false;
			}
			Vertex destinationVertex = graph.getVertex(destination);
			if (destinationVertex == null) {
				return false;
			}
			return hasRoute(originVertex, destinationVertex, new HashSet<>());
		}
	}

	/**
	 * This method with private scope will be called recursively to iterate all the
	 * adjacent vertices. All the visited vertices will be stored in visitedVertices
	 * set to not to visit again and not get into the loop while traversing the
	 * graph. All the adjacent vertices which are not already visited will be added
	 * to the queue to traverse the graph next level.
	 * 
	 * @param originVertex
	 * @param destinationVertex
	 * @param visitedVertices
	 * @return
	 */
	private boolean hasRoute(Vertex originVertex, Vertex destinationVertex, Set<String> visitedVertices) {
		visitedVertices.add(originVertex.getValue());
		Iterator<Vertex> adjacentIterator = originVertex.getAdjacents().iterator();
		Queue<Vertex> verticesToVisit = new LinkedList<>();
		while(adjacentIterator.hasNext()) {
			Vertex adjacentVertex = adjacentIterator.next();
			if (adjacentVertex.equals(destinationVertex)) {
				return true;
			}
			verticesToVisit.add(adjacentVertex);
		}
		Vertex nextVertex = null;
		while ((nextVertex = verticesToVisit.poll()) != null) {
			if (!visitedVertices.contains(nextVertex.getValue())) {
				logger.debug("Traversing to {}", nextVertex);
				if (hasRoute(nextVertex, destinationVertex, visitedVertices)) {
					logger.debug("Route {}", visitedVertices);
					return true;
				}
			}
		}
		return false;
	}
}
