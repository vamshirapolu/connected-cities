package com.cc.routefinder.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex {
	private String value;
	private Set<Vertex> adjacents = new HashSet<>();

	public Vertex(String value) {
		super();
		this.value = value;
	}

	public void addAdjacentVertex(Vertex vertex) {
		adjacents.add(vertex);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Vertex> getAdjacents() {
		return adjacents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("Vertex [value=").append(value).append(", adjacents=");
		List<String> adjacentVertices = new ArrayList<>();
		adjacents.forEach(vertex -> adjacentVertices.add(vertex.getValue()));
		buffer.append(adjacentVertices);
		buffer.append("]");
		return buffer.toString();
	}

}
