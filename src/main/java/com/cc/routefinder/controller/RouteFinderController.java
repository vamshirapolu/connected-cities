package com.cc.routefinder.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cc.routefinder.service.RouteFinderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * This class will map the rest end point and calls the underlying service
 * methods.
 * 
 * @author Vamshi
 *
 */
@RestController
public class RouteFinderController {

	@Autowired
	private RouteFinderService service;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@ApiOperation(value = "Finds the route between two cities", response = String.class)
	@RequestMapping(method = RequestMethod.GET, path = "/connected", produces = "text/plain")
	public String greeting(
			@ApiParam(value = "Origin City", required = true, example = "Boston") @RequestParam(value = "origin") String origin,
			@ApiParam(value = "Destination City", required = true, example = "New York") @RequestParam(value = "destination") String destination) {
		logger.debug("Finding the route from {} to {}", origin, destination);
		return service.hasRoute(origin, destination) ? "yes" : "no";
	}
}
