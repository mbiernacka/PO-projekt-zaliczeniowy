package org.example.boundary;


import org.example.AbstractWorldMap;
import org.example.Animal;
import org.example.Vector2d;
import org.example.interfaces.IPositionChangeObserver;

import java.util.Collections;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary  {

    private  Vector2d size;
    public MapBoundary(Vector2d size){//interface


    }



    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

    public Vector2d getUpperRight() {
        return this.size;
    }

}

