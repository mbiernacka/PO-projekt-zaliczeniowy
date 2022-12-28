package org.example.boundary;
import org.example.Animal;
import org.example.BasicMap;
import org.example.boundary.MapBoundary;
import org.example.Vector2d;

public class BoundaryGlobe extends BasicMap
{


    public BoundaryGlobe(int grassAmount, Vector2d size) {
        super(grassAmount, size);
    }

    public Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){

        if (newVector.x <0 || newVector.y<0) {
            return new Vector2d(((int)Math.random()* 1000)% super.getUpperRight().x ,((int)Math.random()* 1000)% super.getUpperRight().y );
            //return new Vector2d(5,5);
        }

        return newVector;
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        super.positionChanged(oldPosition, verifyMove(oldPosition,newPosition), animal);
    }
}
