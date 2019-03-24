package com.cc.routefinder.data.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the Graph data structure holds the reference index to all the
 * vertices.
 * 
 * @author Vamshi
 *
 */
public class Graph {

	private Map<String, Vertex> graphIndex = new HashMap<>();

	/**
	 * This method will return the vertex by name if it was already added to the
	 * graph. If it is not exist in the graph data structure, it will instantiate
	 * new vertex with the name and add it to the graph index and return the vertex
	 * object.
	 * 
	 * @param value
	 * @return
	 */
	public Vertex getVertex(String value) {
		if (!graphIndex.containsKey(value)) {
			graphIndex.put(value, new Vertex(value));
		}
		return graphIndex.get(value);
	}

	/**
	 * This method will add the edge from origin vertex to the destination vertex by
	 * vertex name.
	 * 
	 * @param origin
	 * @param destination
	 */
	public void addEdge(String origin, String destination) {
		addEdge(getVertex(origin), getVertex(destination));
	}

	/**
	 * This method will add the edge from origin vertex to the destination vertex by
	 * vertex object. Both vertices will be added as adjacent vertices in each
	 * other. The routes from city routes file are considered as bidirectional.
	 * 
	 * @param sourceVertex
	 * @param destinationVertex
	 */
	public void addEdge(Vertex sourceVertex, Vertex destinationVertex) {
		sourceVertex.addAdjacentVertex(destinationVertex);
		destinationVertex.addAdjacentVertex(sourceVertex);
	}
}
