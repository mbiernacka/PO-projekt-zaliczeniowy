package org.example.boundary;


import org.example.Vector2d;

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

