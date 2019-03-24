package com.cc.routefinder.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cc.routefinder.graph.Graph;

@Component
public class RouteFinderRepository {

	@Value("${routefinder.city.route.file}")
	private String cityRouteFile;

	private Graph graph = new Graph();

	public Graph getCityRoutes() {
		return this.graph;
	}

	@PostConstruct
	public void initialize() throws IOException {

		Resource resource = new ClassPathResource(cityRouteFile);
		InputStream resourceAsStream = resource.getInputStream();
		Scanner fileScanner = new Scanner(resourceAsStream);
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
