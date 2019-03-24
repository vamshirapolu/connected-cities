package com.cc.routefinder.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.routefinder.service.RouteFinderService;


@RestController
public class RouteFinderController {

	@Autowired
	private RouteFinderService service;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@GetMapping("/connected")
	public String greeting(@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination) {
		logger.debug("Finding the route from {} to {}", origin, destination);
		return service.hasRoute(origin, destination) ? "yes" : "no";
	}
}
