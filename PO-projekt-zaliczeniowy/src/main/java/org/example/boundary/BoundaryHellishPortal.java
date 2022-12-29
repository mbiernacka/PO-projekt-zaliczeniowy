package org.example.boundary;

import org.example.BasicMap;
import org.example.Vector2d;

public class BoundaryHellishPortal extends BasicMap
{


    public BoundaryHellishPortal(int grassAmount, Vector2d size) {
        super(grassAmount, size);
    }

    public Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){

        if (newVector.x <0 || newVector.y<0) {
            return new Vector2d(((int)Math.random()* 1000)% super.getUpperRight().x ,((int)Math.random()* 1000)% super.getUpperRight().y );
            //return new Vector2d(5,5);
        }

        return newVector;
    }

//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        // newPosition =  verifyMove(newPosition);
//
//        super.xSet.remove(oldPosition);
//        super.ySet.remove(oldPosition);
//        super.xSet.add(newPosition);
//        super.ySet.add(newPosition);
//
//    }

}
