package org.example;

import java.util.*;

import org.example.interfaces.IWorldMap;

public abstract class AbstractWorldMap implements IWorldMap{
    protected Map<Vector2d, ArrayList<Animal>> animalMap;
    protected final MapVisualizer mapVisualizer;
    protected Map<Vector2d, Plant> grassMap;
    protected AbstractWorldMap(){
        this.animalMap = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);

    }


    public String toString(){
        return mapVisualizer.draw(calculateLowerBound(), calculateUpperBound());
    }

    public abstract Object objectAt(Vector2d position);

    public abstract Vector2d calculateLowerBound();
    public abstract Vector2d calculateUpperBound();

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal a){
        ArrayList<Animal> animalList = ((ArrayList<Animal>) this.objectAt(oldPosition));
        Animal animal = animalList.get(animalList.indexOf(a));

        this.animalMap.get(oldPosition).remove(animal);
        //System.out.println("tu chcemy nulla" + animalMap.get(oldPosition));
        this.animalMap.putIfAbsent(newPosition, new ArrayList<>());
        this.animalMap.get(newPosition).add(animal);
        //System.out.println("tu chcemy zwierze" + animalMap.get(newPosition));

    }

    public Map<Vector2d, ArrayList<Animal>> getAnimalMap() {
        return Map.copyOf(this.animalMap);
    }

    public void place(Animal animal){
        Vector2d position = animal.getPosition();
        //coś jest

        animal.addObserver(this);
        this.animalMap.putIfAbsent(position, new ArrayList<>());
        this.animalMap.get(position).add(animal);
    }
    public void removeDeadAnimalAndMove(){
        ArrayList<Vector2d> keyList = new ArrayList<Vector2d>();
        ArrayList<Animal> animalList = new ArrayList<Animal> ();

        ArrayList<Animal> aliveAnimalList = new ArrayList<>();


        animalMap.forEach((key, value)->{

            for (Animal animal:
                    value) {
                if(animal.isDead()){
                    keyList.add(key);
                    animalList.add(animal);
//        this.animalMap.get(key).remove(animal);
                }else {
                   //aliveKeyList.add(key);
                   aliveAnimalList.add(animal);
                }
            }

        });
        for (int i = 0; i < keyList.size(); i++) {
            this.animalMap.get(keyList.get(i)).remove(animalList.get(i));
        }
        for (int i = 0; i < aliveAnimalList.size(); i++) {
            aliveAnimalList.get(i).move();
        }

//todo:to można przez iterator- jakoś
//        Iterator mapIterator = animalMap.entrySet().iterator();
//        while (mapIterator.hasNext()){
//           System.out.println(mapIterator);
//        }
    }

    public void sortAnimalMap(){

        animalMap.forEach((key, value)->{

            value.sort(Comparator.comparing(Animal::getEnergy)
                            .thenComparing(Animal::getAge)
                            .thenComparing(Animal::getNumberOfKids).reversed());
            });

        }


    public abstract Vector2d verifyMove(Vector2d oldVector, Vector2d newVector);

    }


