package org.example.boundary;


import org.example.Animal;
import org.example.Vector2d;
import org.example.interfaces.IPositionChangeObserver;

import java.util.Collections;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final TreeSet<Vector2d> xSet;
    private final TreeSet<Vector2d> ySet;
    private  Vector2d size;
    public MapBoundary(Vector2d size){//interface
        this.xSet = new TreeSet<>(Comparator.comparingInt(o -> o.x));
        this.ySet = new TreeSet<>(Comparator.comparingInt(o -> o.y));
        this.size = size;
    }
    //tutajjj
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
       // newPosition =  verifyMove(newPosition);

        this.xSet.remove(oldPosition);
        this.ySet.remove(oldPosition);
        this.xSet.add(newPosition);
        this.ySet.add(newPosition);

    }

    public void place(Vector2d position){
        xSet.add(position);
        ySet.add(position);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

    public Vector2d getUpperRight() {
        return this.size;
    }



}

