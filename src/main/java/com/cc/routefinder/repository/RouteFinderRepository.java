package com.cc.routefinder.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class RouteFinderRepository {

	@Value("${routefinder.city.route.file}")
	private String cityRouteFile;

	private static Map<String, String> cityRouteMap = new HashMap<>();

	public static Map<String, String> getCityRouteMap() {
		return cityRouteMap;
	}

	public static void setCityRouteMap(Map<String, String> cityRouteMap) {
		RouteFinderRepository.cityRouteMap = cityRouteMap;
	}

	@PostConstruct
	public void initialize() throws IOException {

		Resource resource = new ClassPathResource(cityRouteFile);
		InputStream resourceAsStream = resource.getInputStream();
		Scanner fileScanner = new Scanner(resourceAsStream);
		Scanner lineScanner = null;

		// TODO: HashMap should replace with a Graph data structure.
		while (fileScanner.hasNextLine()) {
			lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter(",");
			while (lineScanner.hasNext()) {
				cityRouteMap.put(lineScanner.next().trim(), lineScanner.next().trim());
			}
		}
		lineScanner.close();
		fileScanner.close();
	}
}
