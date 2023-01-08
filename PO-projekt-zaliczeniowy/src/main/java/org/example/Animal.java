package org.example;

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
    protected Integer[] genotype;
    private int energy;
    private int age=0;
    private int numberOfKids=0;
    private final int MOVE_COST = 2;
    private int currentGene = 0;
private int plantsEaten =0;

    private int nextGene(){
        int curr = currentGene; //6
        currentGene++;//7
        currentGene = currentGene % NUMBER_OF_GENES;//0
        return curr;//6
    }

    public int getPlantsEaten() {
        return plantsEaten;
    }

    private final List<IPositionChangeObserver> observerList;
//dla pierwszych
    public Animal(IWorldMap map, Vector2d initialPosition, int baseEnergy, int numOfGenes){
        this(map,initialPosition,IntStream.of(new Random().ints(numOfGenes, 0, 8).toArray() ).boxed().toArray( Integer[]::new ), baseEnergy, numOfGenes);
        this.currentGene =0;
    }
//dla dzieci
    public Animal(IWorldMap map, Vector2d initialPosition,Integer[] genes, int baseEnergy, int numOfGenes){
        {
            this.currentGene = new Random().nextInt((numOfGenes));
            this.orientation = MapDirection.NORTH;
            this.position = initialPosition;
            this.energy = baseEnergy;
            this.map = map;
            this.NUMBER_OF_GENES = numOfGenes;
            this.genotype = genes;
            this.observerList = new ArrayList<>();
            map.place(this);
        }
    }

    public int getNUMBER_OF_GENES() {
        return NUMBER_OF_GENES;
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
            this.plantsEaten++;
        }
    }

    public void increaseNumberOfKids(){
        this.numberOfKids++;
    }
    public void decreaseEnergy(int value){
        this.energy -= value;
        if(this.energy < 0 ){
            this.energy = 0;
        }
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
        //return energy + ",";
        return switch (this.orientation){
            case NORTH -> "Animal\nN, " + this.getEnergy() + ", " + this.getAge();
            case EAST -> "Animal\nE, "+ this.getEnergy() + ", " + this.getAge();
            case SOUTH -> "Animal\nS, "+ this.getEnergy() + ", " + this.getAge();
            case WEST -> "Animal\nW, "+ this.getEnergy() + ", " + this.getAge();
            case NORTHEAST -> "Animal\nNE, "+ this.getEnergy() + ", " + this.getAge();
            case SOUTHEAST -> "Animal\nSE, "+ this.getEnergy() + ", " + this.getAge();
            case NORTHWEST -> "Animal\nNW, "+ this.getEnergy() + ", " + this.getAge();
            case SOUTHWEST -> "Animal\nSW, "+ this.getEnergy() + ", " + this.getAge();
        };
    }

   //genotpyetostring
    private String genotypeToString() {
        StringBuilder genotypeString = new StringBuilder("G:");
        for (Integer i : this.getGenotype()) {
            genotypeString.append(i);
        }
    return genotypeString.toString();
    }
    public String trackedAnimalStats() {
        return String.format("""
                        
                        Tracked Animal:
                        genotype: %s
                        activated gene: %d
                        energy: %d
                        plants eaten %d
                        number of children: %d
                        age: %d """,this.genotypeToString(),
                this.genotype[this.currentGene], this.getEnergy(), this.getPlantsEaten(), this.getNumberOfKids(), getAge());
    }

    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }

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
        Vector2d animalNewPosision = map.verifyMove(this.position, this.position.add(this.orientation.toUnitVector()));
       this.positionChanged(this.position ,animalNewPosision, this);

        this.position = animalNewPosision;

        System.out.println(this.position.toString());
        System.out.println(newOrientation);
    }


    public void addObserver(IPositionChangeObserver observer){
        this.observerList.add(observer);
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal a){
        for (IPositionChangeObserver observer: this.observerList){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return NUMBER_OF_GENES == animal.NUMBER_OF_GENES && energy == animal.energy && age == animal.age && numberOfKids == animal.numberOfKids && MOVE_COST == animal.MOVE_COST && currentGene == animal.currentGene && orientation == animal.orientation && Objects.equals(position, animal.position) && Objects.equals(map, animal.map) && Arrays.equals(genotype, animal.genotype) && Objects.equals(observerList, animal.observerList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(NUMBER_OF_GENES, orientation, position, map, energy, age, numberOfKids, MOVE_COST, currentGene, observerList);
        result = 31 * result + Arrays.hashCode(genotype);
        return result;
    }
}
