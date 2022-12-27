package org.example.boundary;
import org.example.boundary.MapBoundary;
import org.example.Vector2d;

public class BoundaryGlobe extends MapBoundary
{

    public BoundaryGlobe(Vector2d size) {
        super(size);
    }

    Vector2d verifyMove(Vector2d oldVector, Vector2d newVector){

        if (newVector.x <0 || newVector.y<0) {
            return new Vector2d(((int)Math.random()* 1000)% super.getUpperRight().x ,((int)Math.random()* 1000)% super.getUpperRight().y );
            //return new Vector2d(5,5);
        }

        return newVector;
    }

}
