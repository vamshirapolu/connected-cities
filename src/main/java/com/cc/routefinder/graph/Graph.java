package com.cc.routefinder.graph;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graph {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Map<String, Vertex> graphIndex = new HashMap<>();

	public Vertex getVertex(String value) {
		if (!graphIndex.containsKey(value)) {
			graphIndex.put(value, new Vertex(value));
		}
		return graphIndex.get(value);
	}

	public void addEdge(String origin, String destination) {
		addEdge(getVertex(origin), getVertex(destination));
	}

	public void addEdge(Vertex sourceVertex, Vertex destinationVertex) {
		if (!graphIndex.containsKey(sourceVertex.getValue())) {
			graphIndex.put(sourceVertex.getValue(), sourceVertex);
		}
		if (!graphIndex.containsKey(destinationVertex.getValue())) {
			graphIndex.put(destinationVertex.getValue(), destinationVertex);
		}
		sourceVertex.addAdjacentVertex(destinationVertex);
		destinationVertex.addAdjacentVertex(sourceVertex);
	}
}
