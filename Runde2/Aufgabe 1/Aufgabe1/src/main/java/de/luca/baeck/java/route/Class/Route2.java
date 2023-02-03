package de.luca.baeck.java.route.Class;

import java.util.List;

import de.luca.baeck.java.point.Interface.Point;
import de.luca.baeck.java.route.AbstractClass.AbstractRoute;
import de.luca.baeck.java.route.Interface.Route;
import de.luca.baeck.java.vector.Class.Vector2;
import de.luca.baeck.java.vector.Interface.Vector;

public class Route2 extends AbstractRoute {
    
    public Route2(Vector[] vectors, List<Point> points) {
        super(vectors, points);
    }

    public Route2(Vector2[] vectors) {
        super(vectors);
    }

    public Route2() {
        super();
    }

    @Override
    public void calculateVectors() {
        vectors = new Vector2[points.size()-1];
        for(int i = 1; i < points.size(); i++) {
            vectors[i-1] = points.get(i-1).getConnectionVector(points.get(i));
        }
    }

    @Override
    public Route cloneRoute() {
        return new Route2(vectors, points);
    }
}
