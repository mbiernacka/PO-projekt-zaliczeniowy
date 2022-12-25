package org.example;

public class MapBoundaryAlternative extends MapBoundary
{
    public MapBoundaryAlternative(Vector2d size) {
        super(size);
    }

    @Override
    public Vector2d verifyMove(Vector2d vector2d){
        return null;
    }
}
