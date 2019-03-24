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

import com.cc.routefinder.graph.Graph;
import com.cc.routefinder.graph.Vertex;
import com.cc.routefinder.repository.RouteFinderRepository;

@Service
public class RouteFinderService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private RouteFinderRepository repository;

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
			logger.debug("Traversing to {}", nextVertex);
			if (!visitedVertices.contains(nextVertex.getValue())) {
				if (hasRoute(nextVertex, destinationVertex, visitedVertices))
					return true;
			}
		}
		return false;
	}
}
