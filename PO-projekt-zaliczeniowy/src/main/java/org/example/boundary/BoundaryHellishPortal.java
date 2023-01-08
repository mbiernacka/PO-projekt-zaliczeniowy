package org.example.boundary;

import org.example.Animal;
import org.example.BasicMap;
import org.example.Vector2d;

public class BoundaryHellishPortal extends BasicMap
{


    public BoundaryHellishPortal(int grassAmount, Vector2d size, int PLANT_NUTRITIOUSNESS, int energyDecrease) {
        super(grassAmount, size,PLANT_NUTRITIOUSNESS,energyDecrease);
    }

    public Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){
        int maxX = super.getUpperRight().x;
        int maxY = super.getUpperRight().y;
        if (newVector.x <0||maxX  < newVector.x|| newVector.y < 0||maxY < newVector.y) {
            return new Vector2d(((int)(Math.random()* 1000))% maxX ,((int)(Math.random()* 1000)% maxY ));
        }

        return newVector;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        super.positionChanged(oldPosition, verifyMove(oldPosition,newPosition), animal);
    }

}
