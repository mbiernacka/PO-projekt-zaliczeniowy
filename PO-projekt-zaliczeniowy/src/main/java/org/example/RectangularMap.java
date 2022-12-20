package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;
    private final Vector2d lowerBound;
    private final Vector2d upperBound;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        upperBound = new Vector2d(width-1,height-1);
        lowerBound = new Vector2d(0,0);
    }

    public boolean canMoveTo(Vector2d position){
//        if(position.follows(lowerBound) && position.precedes(upperBound) && (isOccupied(position)==false)){
//            return true;
//        };
//        return false;
        // w sumie to zawsze jeśli pole nie jest zjęcte można się ruszyć, dopiero po ruchu mogą dziać się różne rzeczy
        return (isOccupied(position)==false);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Vector2d calculateLowerBound() {
        return this.lowerBound;
    }

    @Override
    public Vector2d calculateUpperBound() {
        return this.upperBound;
    }
}
