package de.luca.baeck.java.vector.AbstractClass;

import de.luca.baeck.java.vector.Interface.Vector;

public abstract class AbstractVector implements Vector {
    
    protected double[] coordinates;

    protected AbstractVector(double[] coordinates) {
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
    public double getMagnitude() {
        double value = 0;
        for(int i = 0; i < coordinates.length; i++) {
            value = value + Math.pow(coordinates[i], 2);
        }
        return Math.sqrt(value);
    }

    @Override
    public void scale(double factor) {
        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = coordinates[i] * factor;
        }
    }

    @Override
    public double scalarMultiplication(Vector factor) {
        if(!this.getClass().equals(factor.getClass())) {
            throw new RuntimeException("Can not handle two different types of vectors");
        }
        double value = 0;
        for(int i = 0; i < coordinates.length; i++) {
            value = value + coordinates[i] * factor.getCoordinates()[i];
        }
        return value;
    }

    @Override
    public double getAngle(Vector secondVector) {
        return Math.toDegrees(Math.acos(Math.abs(this.scalarMultiplication(secondVector)) / (this.getMagnitude() * secondVector.getMagnitude())));
    }
}
