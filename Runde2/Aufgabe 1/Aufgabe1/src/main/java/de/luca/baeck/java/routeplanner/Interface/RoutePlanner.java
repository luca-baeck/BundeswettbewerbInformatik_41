package de.luca.baeck.java.routeplanner.Interface;

import de.luca.baeck.java.route.Interface.Route;

public interface RoutePlanner {

    //angles on route have to be below 90°
    //all points have to bee visited
    //shortest possible route
    public Route execute();
    
}
