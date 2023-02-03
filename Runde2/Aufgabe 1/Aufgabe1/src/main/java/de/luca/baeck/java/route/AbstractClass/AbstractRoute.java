package de.luca.baeck.java.route.AbstractClass;

import java.util.ArrayList;
import java.util.List;

import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.route.Interface.Route;
import de.luca.baeck.java.vector.Interface.Vector;

public abstract class AbstractRoute implements Route {
    
    protected Vector[] vectors;
    protected List<Point> points;

    public AbstractRoute(Vector[] vectors, List<Point> points) {
        this.vectors = vectors;
        this.points = new ArrayList<>(points);
    }

    protected AbstractRoute(Vector[] vectors) {
        this.vectors = vectors;
        this.points = new ArrayList<>();
    }

    protected AbstractRoute() {
        this.points = new ArrayList<>();
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public void remove(Point point) {
        this.points.remove(point);
    }

    @Override
    public Vector[] getVectors() {
        return vectors;
    }

    @Override
    public void setVectors(Vector[] vectors) {
        this.vectors = vectors;
    }

    @Override
    public double getLength() {
        double length = 0;
        for(int i = 0; i < vectors.length; i++) {
            length = length + vectors[i].getMagnitude();
        }
        return length;
    }

    @Override
    public String toString() {
        String string = "Route(Length: " + getLength() + "km / Points: " + vectors.length + ")\nPoints in order:\n";
        int counter = 1;
        for(Point point : points) {
            string = string + counter +  ". x:" + point.getCoordinates()[0] + " y:" + point.getCoordinates()[1] + "\n";
            counter++;
        }
        return string;
    }
}
