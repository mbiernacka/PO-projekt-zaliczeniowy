package org.example;

import java.util.*;
import org.example.interfaces.IWorldMap;

public abstract class AbstractWorldMap implements IWorldMap{
    protected Map<Vector2d, ArrayList<Animal>> animalMap;
    protected final MapVisualizer mapVisualizer;

    protected AbstractWorldMap(){
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }


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
        this.animalMap.get(oldPosition).remove(animal);
        this.animalMap.putIfAbsent(newPosition, new ArrayList<>());
        this.animalMap.get(newPosition).add(animal);

    }

    public Map<Vector2d, ArrayList<Animal>> getAnimalMap() {
        return Map.copyOf(this.animalMap);
    }

    public void place(Animal animal){
        Vector2d position = animal.getPosition();
        //coś jest

        //
        this.animalMap.put(position, animalMap.get(position));
    }
}
