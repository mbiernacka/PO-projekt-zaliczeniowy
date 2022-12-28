package org.example.boundary;

import org.example.BasicMap;
import org.example.Vector2d;

public class BoundaryHellishPortal extends BasicMap
{


    public BoundaryHellishPortal(int grassAmount, Vector2d size) {
        super(grassAmount, size);
    }

    public Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){
        Vector2d outputV = new Vector2d(newVector.x, newVector.y);
        int maxX = super.getUpperRight().x;
        int maxY = super.getUpperRight().y;

        if (newVector.x <0) {
            outputV = new Vector2d(maxX, newVector.y);
        } else if(maxX  < newVector.x ){
            outputV = new Vector2d(0, newVector.y);
        }

        if (newVector.y < 0) {
            outputV = new Vector2d(newVector.x, maxY);
        } else if (maxY < newVector.y) {
            outputV = new Vector2d(newVector.x, 0);
        }

        System.out.println("strara: " + newVector);
        System.out.println("nowa pozycja: " +outputV);
        return outputV;
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
