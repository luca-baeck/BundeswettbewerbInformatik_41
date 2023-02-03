package de.luca.baeck.java.point.Interface;

import de.luca.baeck.java.vector.Interface.Vector;

public interface Point {
    
    public double[] getCoordinates();

    public void setCoordinates(double[] newCoordinates);

    public Vector getConnectionVector(Point target);

}
