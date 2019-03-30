# connected-cities #

Program to check if the route exists between two cities.

The default city routes are loading form the ` city.txt ` file under resources folder. This file name is configured in the ` application.properties ` file.

## Implementation ##

Since the objective is to find the route between the different cities and every city will have one or more routes to other cities, the **graph data structure** has been chosen. On spring boot start-up, the file with city routes will be read and loaded into the graph data structure and make it available for subsequent request of finding the route between provided cities. To find the route between cities, the **breadth-first search (BFS)** algorithm is used.

## Dependencies ##

This project has a dependency on [Java8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://maven.apache.org/install.html) to run. Spring boot is using the default port (8080) to start the standalone. Make sure the port 8080 is free and not been used by any other application.

## Facts to surface ##

- City routes file ` city.txt ` is read considering it doesn’t have any empty lines or lines with only spaces.
- Every line in the ` city.txt ` file will have origin and destination cities with comma separated.
- Origin and Destination names are case sensitive
- Self-root always returns “no”, even if the route exists in the ` city.txt ` (can be handled if needed)
- If the provided origin or destination does not exist in ` city.txt `, it will return “no”
- The routes from ` city.txt ` are considered as bidirectional routes. 

## How to run? ##

Create the package using maven. This will compile the code, run the unit test and create the package.

    mvn package

Run the spring boot standalone using the maven 

    mvn exec:java

## Swagger Documentation ##

Once the spring boot standalone is successfully started the swagger documentation for connected route endpoint can be accessible using [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![Swagger UI](https://i.imgur.com/Hxp0ikw.jpg)