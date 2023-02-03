package de.luca.baeck.java.vector.Class;

import de.luca.baeck.java.vector.AbstractClass.AbstractVector;
import de.luca.baeck.java.vector.Interface.Vector;

public class Vector2 extends AbstractVector {

    public Vector2(double[] coordinates) {
        super(coordinates);
    }

    public Vector2(double x, double y) {
        super(new double[] {x,y});    
    }
    
    @Override
    public Vector add(Vector summand) {
        if(!this.getClass().equals(summand.getClass())) {
            throw new RuntimeException("Can not handle two different types of vectors");
        }
        double[] newCoordinates = new double[coordinates.length];
        for(int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = coordinates[i] + summand.getCoordinates()[i];
        }
        return new Vector2(newCoordinates);
    }

    @Override
    public Vector substract(Vector subtrahend) {
        if(!this.getClass().equals(subtrahend.getClass())) {
            throw new RuntimeException("Can not handle two different types of vectors");
        }
        double[] newCoordinates = new double[coordinates.length];
        for(int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = coordinates[i] - subtrahend.getCoordinates()[i];
        }
        return new Vector2(newCoordinates);
    }
}
