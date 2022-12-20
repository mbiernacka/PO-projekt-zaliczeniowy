package org.example;

import org.example.interfaces.IWorldMap;

import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private Genotype genotype; //nowa klasa
    private int energy;
    /*
    zwiększanie energi po jedzeniu
    sprawdzanbie czyt nie umarło
    mniejszanie(int)
    get energy
     */
    //private final List<IPositionChangeObserver> observerList;
    public Animal (IWorldMap map){
        this(map, new Vector2d(2,2));
    }
    private int order;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        //this.observerList = new ArrayList<>();
    }


    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }


    @Override
    public String toString() {
        return switch (this.orientation){
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            case NORTHEAST -> "NE";
            case SOUTHEAST -> "SE";
            case NORTHWEST -> "NW";
            case SOUTHWEST -> "SW";
        };
    }

    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }


    public void move(MoveDirection direction){
        Vector2d move = new Vector2d(0,0);
        MapDirection newOrientation = this.orientation;
        switch(direction){
            case LEFT -> newOrientation = newOrientation.previous();
            case RIGHT -> newOrientation = newOrientation.next();
            case BACKWARD -> move = this.orientation.toUnitVector().opposite();
            case FORWARD -> move = this.orientation.toUnitVector();
        }

        Vector2d newPosition = this.position.add(move);

        this.orientation = newOrientation;

        if(this.map.canMoveTo(newPosition)){
            this.positionChanged(this.position, newPosition);
            this.position =  newPosition;
        }
    }

//    public void addObserver(IPositionChangeObserver observer){
//        this.observerList.add(observer);
//    }
//    public void removeObserver(IPositionChangeObserver observer){
//        this.observerList.remove(observer);
//    }

//    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//        for (IPositionChangeObserver observer: this.observerList){
//            observer.positionChanged(oldPosition, newPosition);
//        }
//    }
}
