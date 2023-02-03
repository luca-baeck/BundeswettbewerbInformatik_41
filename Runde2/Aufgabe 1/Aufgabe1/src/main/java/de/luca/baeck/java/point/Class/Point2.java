package de.luca.baeck.java.point.Class;

import de.luca.baeck.java.point.AbstractClass.AbstractPoint;
import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.vector.Class.Vector2;
import de.luca.baeck.java.vector.Interface.Vector;

public class Point2 extends AbstractPoint {

    public Point2(double[] coordinates) {
        super(coordinates);
    }

    public Point2(double x, double y) {
        super(new double[] {x,y});    
    }

    @Override
    public Vector getConnectionVector(Point target) {
        if(!this.getClass().equals(target.getClass())) {
            throw new RuntimeException("Can not handle two different types of points");
        }
        double[] newCoordinates = new double[coordinates.length];
        for(int i = 0; i < coordinates.length; i++) {
            newCoordinates[i] = target.getCoordinates()[i] - coordinates[i];
        }
        return new Vector2(newCoordinates);
    }  
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point2)) {
            return false;
        }
        Point2 c = (Point2) o;
        return (((int)this.getCoordinates()[0] == (int)c.getCoordinates()[0]) && ((int)this.getCoordinates()[1] == (int)c.getCoordinates()[1]));
    } 
}
           