package de.luca.baeck.java.point.AbstractClass;

import de.luca.baeck.java.point.Interface.Point;

public abstract class AbstractPoint implements Point {
    
    protected double[] coordinates;

    protected AbstractPoint(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(double[] newCoordinates) {
        coordinates = newCoordinates;
    }
    
    @Override
    public String toString() {
        return "X-Value: " + coordinates[0] + " --- Y-Value: " + coordinates[1];
    }
}
