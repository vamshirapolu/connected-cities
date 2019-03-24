package com.cc.routefinder.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.routefinder.repository.RouteFinderRepository;

@Service
public class RouteFinderService {

	@Autowired
	private RouteFinderRepository repository;

	public boolean hasRouteExist(String origin, String destination) {
		return hasRouteExist(origin, destination, new HashSet<>());
	}

	private boolean hasRouteExist(String origin, String destination, Set<String> visited) {
		// TODO: Implement the graph traversal to find the route.
		// This implementation will only works for single level and unidirectional.
		return repository.getCityRouteMap().get(origin).equalsIgnoreCase(destination);
	}
}
