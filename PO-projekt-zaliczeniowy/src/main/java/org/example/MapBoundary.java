package org.example;


import org.example.interfaces.IPositionChangeObserver;

import java.util.Collections;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final TreeSet<Vector2d> xSet;
    private final TreeSet<Vector2d> ySet;

    public MapBoundary(){
        this.xSet = new TreeSet<>(Comparator.comparingInt(o -> o.x));
        this.ySet = new TreeSet<>(Comparator.comparingInt(o -> o.y));
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
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
        Vector2d xRes = xSet.first();
        Vector2d yRes = ySet.first();
        return xRes.lowerLeft(yRes);
    }

    public Vector2d getUpperRight() {
        Vector2d xRes = xSet.last();
        Vector2d yRes = ySet.last();
        return xRes.upperRight(yRes);
    }
    public Vector2d verifyMove(Vector2d vector2d){
        return null;
    }
}

