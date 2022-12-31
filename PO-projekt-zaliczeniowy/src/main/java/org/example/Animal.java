package org.example;

import org.example.boundary.BoundaryHellishPortal;
import org.example.interfaces.IMapElement;
import org.example.interfaces.IPositionChangeObserver;
import org.example.interfaces.IWorldMap;

import java.util.*;
import java.util.stream.IntStream;

public class Animal implements IMapElement {
    protected final int NUMBER_OF_GENES;
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    protected Integer[] genotype; //nowa klasa
    private int energy;
    private int age=0;
    private int numberOfKids=0;
    private final int MOVE_COST = 2;
    private int currentGene = 0;


    private int nextGene(){
        int curr = currentGene; //6
        currentGene++;//7
        currentGene = currentGene % NUMBER_OF_GENES;//0
        return curr;//6
    }

   private List<IPositionChangeObserver> observerList;

    public Animal(IWorldMap map, Vector2d initialPosition, int baseEnergy, int numOfGenes){
        this(map,initialPosition,IntStream.of(new Random().ints(numOfGenes, 0, 8).toArray() ).boxed().toArray( Integer[]::new ), baseEnergy, numOfGenes);
    }

    public Animal(IWorldMap map, Vector2d initialPosition,Integer[] genes, int baseEnergy, int numOfGenes){
        {
            this.orientation = MapDirection.NORTH;
            this.position = initialPosition;
            this.energy = baseEnergy;
            this.map = map;
            this.NUMBER_OF_GENES = numOfGenes;
            this.genotype = genes;
//            System.out.println("Losowy genotyp: " + Arrays.toString(this.genotype));
//            for (int i=0; i <= genotype.length-1; i++){
//                this.genotype[i] = genotype[i];
//            }
            this.observerList = new ArrayList<>();
            map.place(this);
        }
    }

    public int getNUMBER_OF_GENES() {
        return NUMBER_OF_GENES;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String getTexture() {
        return "src/main/resources/Cat_north.png";
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getLabel() {
        return this.getEnergy()+"";
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

    public void increaseNumberOfKids(){
        this.numberOfKids++;
    }
    public void decreaseEnergy(int value){
        this.energy -= value;
    }

    public int getAge() {
        return this.age;
    }

    public int getNumberOfKids() {
        return this.numberOfKids;
    }

    public boolean isDead(){
        return this.energy <= 0;
    }

    @Override
    public String toString() {
        return energy + ",";
//        return switch (this.orientation){
//            case NORTH -> "N";
//            case EAST -> "E";
//            case SOUTH -> "S";
//            case WEST -> "W";
//            case NORTHEAST -> "NE";
//            case SOUTHEAST -> "SE";
//            case NORTHWEST -> "NW";
//            case SOUTHWEST -> "SW";
//        };
    }

    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }

//    public void testMove(int gene){
//        MapDirection newOrientation = this.orientation;
//        int i = 0;
//        while (i < gene){
//            newOrientation = newOrientation.next();
//            i++;
//        }
//        this.orientation = newOrientation;
//        //dodałem tylko tą linijkę, jak coś xd
//        this.positionChanged(this.position ,this.position.add(this.orientation.toUnitVector()), this);
//        this.position = this.position.add(this.orientation.toUnitVector());
//
//        System.out.println(this.position.toString());
//        System.out.println(newOrientation.toString());
//    }

    public void move(){
        age++;
        this.decreaseEnergy(this.MOVE_COST);
        int gene = nextGene();
        MapDirection newOrientation = this.orientation;
        int i = 0;
        while (i < genotype[gene]){
            newOrientation = newOrientation.next();
            i++;
        }
        this.orientation = newOrientation;
        //dodałem tylko tą linijkę, jak coś xd
        Vector2d animalNewPosision = map.verifyMove(this.position, this.position.add(this.orientation.toUnitVector()));
       this.positionChanged(this.position ,animalNewPosision, this);

        this.position = animalNewPosision;

        System.out.println(this.position.toString());
        System.out.println(newOrientation.toString());
    }


    public void addObserver(IPositionChangeObserver observer){
       // this.observerList = new ArrayList<>();//DO USUNICIA
//        if (observerList == null){
//            this.observerList = new ArrayList<>();
//        }
        this.observerList.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        this.observerList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal a){
        for (IPositionChangeObserver observer: this.observerList){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }
}
