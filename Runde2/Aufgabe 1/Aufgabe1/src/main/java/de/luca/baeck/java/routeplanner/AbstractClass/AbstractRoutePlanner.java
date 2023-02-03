package de.luca.baeck.java.routeplanner.AbstractClass;

import java.util.ArrayList;
import java.util.List;

import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.route.Class.Route2;
import de.luca.baeck.java.route.Interface.Route;
import de.luca.baeck.java.routeplanner.Interface.RoutePlanner;
import de.luca.baeck.java.vector.Interface.Vector;

public abstract class AbstractRoutePlanner implements RoutePlanner {

    protected List<Point> points;

    protected AbstractRoutePlanner(List<Point> points) {
        this.points = points;
    }

    @Override
    public Route execute() {
        Route[] results = new Route[points.size()];
        for(Point startPoint : points) {
            results[points.indexOf(startPoint)] = getRoute(startPoint);
        }
        
        int indexLowest = 0;
        for(int i = 0; i < results.length; i++) {
            if(results[i] == null) {
                i++;
                continue;
            }
            if(results[indexLowest].getLength() > results[i].getLength()) {
                indexLowest = i;
            }
        }
        return results[indexLowest];
    }

    private Route getRoute(Point startPoint) {
        Route route = new Route2();
        route.addPoint(startPoint);
        while(route.getPoints().size() < points.size()) {
            List<Point> neighbours = getNeighbours(route);
            if(neighbours.size() == 0) {
                return null;
                //go back remove last point select not lowest -> lowest +1
            }
            Point currentPoint = route.getPoints().get(route.getPoints().size()-1);
            int indexLowest = 0;
            for(int i = 0; i < neighbours.size(); i++) {
                if(currentPoint.getConnectionVector(neighbours.get(indexLowest)).getMagnitude() > currentPoint.getConnectionVector(neighbours.get(i)).getMagnitude()) {
                    indexLowest = i;
                }
            }
            route.addPoint(neighbours.get(indexLowest));
        }
        route.calculateVectors();
        return route;
    }

    private List<Point> getNeighbours(Route route) {
        List<Point> neighbors = new ArrayList<>();
        for(Point point : points) {
            if(route.getPoints().contains(point)){
                continue;
            }
            if(route.getPoints().size() <= 1) {
                neighbors.add(point);
                continue;
            }

            Vector lastPath = route.getPoints().get(route.getPoints().size()-2).getConnectionVector(route.getPoints().get(route.getPoints().size()-1));
            Vector connectionVector = route.getPoints().get(route.getPoints().size()-1).getConnectionVector(point);
            Double angle = lastPath.getAngle(connectionVector);
            if(((180-angle) <= 90) || (angle.isNaN())) {
                neighbors.add(point);
            }
        }
        return neighbors;
    }

    /*
    //get all routes and pick the lowest
    //Problem: long calculating time
    private Route getRoute(List<Route> currentRoutes) {
        List<Route> routes = currentRoutes;
        boolean check = true;
        int counter = 0;
        while(check) {
            System.out.println("Iteration: " + counter);
            counter++;
            check = false;
            List<Route> newRoutes = new ArrayList<>();
            for(Route route : routes) {
                List<Point> neighbours = getNeighbours(route);
                for(Point neighbour : neighbours) {
                    Route cloneRoute = route.cloneRoute();
                    cloneRoute.addPoint(neighbour);
                    newRoutes.add(cloneRoute);
                    check = true;
                }
            }
            routes = newRoutes;
        }
        //check if all points are contained
        return getMinRoute(routes);  
    }

    private Route getMinRoute(List<Route> routes) {
        int indexLowest = 0;
        for(int i = 0; i < routes.size(); i++) {
            routes.get(i).calculateVectors();
            if(routes.get(indexLowest).getLength() < routes.get(i).getLength()) {
                indexLowest = i;
            }
        }
        if(routes.size() == 0) {
            return new Route2();
        }
        return routes.get(indexLowest);
    }
    */

    //Implementation with dijkstra algorithym
    //Problem: can not give a route to every point
    /*
    private Route getRoute(Point startPoint, Point target) {
        List<Point> nodes = new ArrayList<>(points);
        List<Point> settledNodes = new ArrayList<>();
        Map<Point, Integer> dist = new HashMap<>();
        Map<Point, Point> previous = new HashMap<>();

        for(Point p : nodes){
            dist.put(p, Integer.MAX_VALUE);
            previous.put(p, null);
        }
        dist.put(startPoint, 0);
        previous.put(startPoint, startPoint);

        Point currentPoint;

        while(nodes.size() != settledNodes.size()) {
            currentPoint = getMinDistance(dist);

            settledNodes.add(currentPoint);

            for(Point p : getNeighbors(previous, currentPoint, nodes, settledNodes)) {
                Integer newDist = dist.get(currentPoint) + (int)currentPoint.getConnectionVector(p).getMagnitude();
                if(newDist < dist.get(p)) {
                    dist.put(p, newDist);
                    previous.put(p, currentPoint);
                }
            }
        }

        for (Map.Entry<Point, Point> entry : previous.entrySet()) {
            System.out.println(entry.getKey().toString() + "          :          " + entry.getValue().toString().toString());
        }
        for (Map.Entry<Point, Integer> entry : dist.entrySet()) {
            System.out.println(entry.getKey().toString() + "          :          " + entry.getValue().toString().toString());
        }
        return null;
    }

    private Point getMinDistance(Map<Point, Integer> map) {
        int min = Integer.MAX_VALUE; 
        List<Point> minKeys = new ArrayList<> ();
        for(Map.Entry<Point, Integer> entry : map.entrySet()) {
            if(entry.getValue() < min) {
                min = entry.getValue();
                minKeys.clear();
            }
            if(entry.getValue() == min) {
                minKeys.add(entry.getKey());
            }
        }
        return minKeys.get(0);
    }
    */
}
