package com.cc.routefinder.repository;

import java.io.IOException;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cc.routefinder.data.graph.Graph;

/**
 * This class allows to load the city.txt file and build the graph data
 * structure. Once loaded graph data structure will be used to serve repetitive
 * request to find route between the two cities.
 * 
 * @author Vamshi
 *
 */
@Component
public class RouteFinderRepositoryImpl implements RouteFinderRepository {

	@Value("${routefinder.city.route.file}")
	private String cityRouteFile;

	private Graph graph = new Graph();

	@Override
	public Graph getCityRoutes() {
		return this.graph;
	}

	/**
	 * This method will be called after the object constructor being called. This
	 * method will load the content from the city.txt file and construct the graph
	 * data structure.
	 * @see Graph
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	private void initialize() throws IOException {

		Resource resource = new ClassPathResource(cityRouteFile);
		Scanner fileScanner = new Scanner(resource.getInputStream());
		Scanner lineScanner = null;

		while (fileScanner.hasNextLine()) {
			lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter(",");
			while (lineScanner.hasNext()) {
				graph.addEdge(lineScanner.next().trim(), lineScanner.next().trim());
			}
		}
		lineScanner.close();
		fileScanner.close();
	}
}
