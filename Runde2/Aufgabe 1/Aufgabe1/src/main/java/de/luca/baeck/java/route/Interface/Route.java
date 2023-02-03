package de.luca.baeck.java.route.Interface;

import java.util.List;

import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.vector.Interface.Vector;

public interface Route {

    public Vector[] getVectors();

    public List<Point> getPoints();

    public void addPoint(Point point);

    public void remove(Point point);
    
    public void setVectors(Vector[] vectors);

    public void calculateVectors();

    public double getLength();

    public String toString();

    public Route cloneRoute();

}
