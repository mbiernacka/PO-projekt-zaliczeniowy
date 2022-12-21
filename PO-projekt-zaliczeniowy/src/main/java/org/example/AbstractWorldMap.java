package org.example;

import java.util.*;
import org.example.interfaces.IWorldMap;

public abstract class AbstractWorldMap implements IWorldMap{
    protected Map<Vector2d, Animal> animalMap;
    protected final MapVisualizer mapVisualizer;

    protected AbstractWorldMap(){
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }

//    public void place(Animal animal) throws IllegalArgumentException{
//        Vector2d position = animal.getPosition();
//        if(!canMoveTo(position)){
//            throw new IllegalArgumentException("You cannot move to the " + position + " position");
//        }
//        this.animalMap.put(position, animal);
//        animal.addObserver(this);
//        animal.setOrder(this.animalMap.size());
//    }

    public String toString(){
        return mapVisualizer.draw(calculateLowerBound(), calculateUpperBound());
    }

    public Object objectAt(Vector2d position){
        return this.animalMap.getOrDefault(position, null);
    }

    public abstract Vector2d calculateLowerBound();
    public abstract Vector2d calculateUpperBound();

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = (Animal) this.objectAt(oldPosition);
        this.animalMap.remove(oldPosition);
        this.animalMap.put(newPosition, animal);
    }

    public Map<Vector2d, Animal> getAnimalMap() {
        return Map.copyOf(this.animalMap);
    }

    public void place(Animal animal){
        Vector2d position = animal.getPosition();
        this.animalMap.put(position, animal);
    }
}
