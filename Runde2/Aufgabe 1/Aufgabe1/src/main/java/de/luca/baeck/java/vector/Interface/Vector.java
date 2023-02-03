package de.luca.baeck.java.vector.Interface;

public interface Vector {
    
    public double[] getCoordinates();

    public void setCoordinates(double[] newCoordinates);

    public double getMagnitude();

    public void scale(double factor);

    public Vector add(Vector summand);

    public Vector substract(Vector subtrahend);

    public double scalarMultiplication(Vector factor);

    public double getAngle(Vector secondVector);

}
