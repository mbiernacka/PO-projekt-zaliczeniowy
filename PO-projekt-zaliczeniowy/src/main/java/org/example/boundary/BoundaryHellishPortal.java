package org.example.boundary;

import org.example.Vector2d;

public class BoundaryHellishPortal extends MapBoundary
{


    public BoundaryHellishPortal(Vector2d size) {
        super(size);
    }

    public Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){
        Vector2d outputV = new Vector2d(newVector.x, newVector.y);
        int maxX = super.getUpperRight().x;
        int maxY = super.getUpperRight().y;

        if (newVector.x <0) {
            outputV = new Vector2d(maxX, newVector.y);
        }
        if (newVector.y<0) {
            outputV = new Vector2d(newVector.y, maxY);
        }
        if(maxX + 1 < newVector.x){
            outputV = new Vector2d(0, newVector.y);
        }
        if (maxY + 1 < newVector.x) {

            outputV = new Vector2d(newVector.x, 0);
        }

        return outputV;
    }

}
