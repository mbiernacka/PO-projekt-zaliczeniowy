package org.example;

import org.example.interfaces.IWorldMap;

import java.util.Arrays;
import java.util.Objects;

public class Animal {
    public static final int NUMBER_OF_GENES = 7;
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private Integer[] genotype = new Integer[NUMBER_OF_GENES]; //nowa klasa
    private int energy;
    /*
    zwiększanie energi po jedzeniu
    sprawdzanbie czyt nie umarło
    mniejszanie(int)
    get energy
     */
    //private final List<IPositionChangeObserver> observerList;

//    public Animal (IWorldMap map){
//        this(map, new Vector2d(2,2));
//    }

    public Animal(IWorldMap map, Vector2d initialPosition, Integer[]genotype, int energy){
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.energy = energy;
        this.map = map;
        map.place(this);
        for (int i=0; i <= genotype.length-1; i++){
            this.genotype[i] = genotype[i];
        }
        //this.observerList = new ArrayList<>();
    }


    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy(){
        return this.energy;
    }
    public Integer[] getGenotype(){
        return this.genotype;
    }

    protected void mutate(Integer[] newGenotype){
        this.genotype = newGenotype;
    }

    public void eat(int value){
        if (!isDead()){
            this.energy += value;
        }
    }

    public void decreaseEnergy(int value){
        this.energy -= value;
    }
    public boolean isDead(){
        return this.energy <= 0;
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

    public void move(int gene){
         MapDirection newOrientation = this.orientation;
         int i = 0;
         while (i < gene){
             newOrientation = newOrientation.next();
             i++;
         }
         this.orientation = newOrientation;
         //dodałem tylko tą linijkę, jak coś xd
         map.positionChanged(this.position ,this.position.add(this.orientation.toUnitVector()));
         this.position = this.position.add(this.orientation.toUnitVector());

        System.out.println(this.position.toString());
        System.out.println(newOrientation.toString());
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
