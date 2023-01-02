package org.example;

import org.example.boundary.MapBoundary;

import java.lang.Math;
import java.util.*;

public abstract class BasicMap extends AbstractWorldMap {
    private int grassAmount;
    private ArrayList<Vector2d> availableGrassSlots;

    private  Vector2d size;
    private  final int NEW_PLANTS;
    private final int PLANT_NUTRITIOUSNESS;
    private final int energyDecrease;
//new v2

    public BasicMap( int grassAmount, Vector2d size, int PLANT_NUTRITIOUSNESS, int energyDecrease){
        this.grassAmount =grassAmount;
        this.grassMap = new HashMap<>();
        this.size = size;
        this.PLANT_NUTRITIOUSNESS = PLANT_NUTRITIOUSNESS;
        this.energyDecrease = energyDecrease;
        availableGrassSlots = new ArrayList<>();

        for (int i = 0; i <= this.size.x; i++) {
            for (int j = 0; j <= this.size.y; j++) {
                availableGrassSlots.add(new Vector2d(i,j));
            }
        }
        this.NEW_PLANTS = grassAmount;
        createPlant();

    }



    public Map<Vector2d, Plant> getGrassMap() {
        return  Map.copyOf(this.grassMap);
    }

    public void createPlant(){
        for (int i = 0; i < this.NEW_PLANTS; i++) {
            if(grassMap.size() == (size.x + 1) * (size.y + 1)) {
                break;
            }
            if ((int) (1 + Math.random() * 10) < 8) {
                centerPlant();//losowa pozycja z centralnego
            } else {
                randomPlant();
            }

        }
    }
    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

    public Vector2d getUpperRight() {
        return this.size;
    }

    protected void randomPlant(){

        Collections.shuffle(availableGrassSlots);
        grassMap.put(availableGrassSlots.get(0), new Plant(availableGrassSlots.get(0)));
        availableGrassSlots.remove(0);


    }

    protected void centerPlant(){
        int centerY = this.getUpperRight().y /2;

        int iterationCounter = 0;
        boolean stop = false;

        for (int i = centerY; i <= this.getUpperRight().y; i++) {
            iterationCounter++;
            //towrzenie tablicy x dla każdego y
            ArrayList potentialX = new ArrayList<Integer>();
            for (int j = 0; j <= getUpperRight().x; j++) {
                potentialX.add(j);
            }
            Collections.shuffle(potentialX);
            //sprawdzanie czy x jest dostępny
            Iterator it = potentialX.iterator();
            while (it.hasNext()){
                //w górę
                int j = (Integer) it.next();
                Vector2d potentialPosition = new Vector2d(j,i);
                if(availableGrassSlots.contains(potentialPosition)){
                    availableGrassSlots.remove(potentialPosition);
                    grassMap.put(potentialPosition, new Plant(potentialPosition));
                    stop = true;
                    break;

                }
                //w dół
                potentialPosition = new Vector2d(j,i - (2*iterationCounter));
                if(availableGrassSlots.contains(potentialPosition)){
                    availableGrassSlots.remove(potentialPosition);
                    grassMap.put(potentialPosition, new Plant(potentialPosition));
                    stop = true;
                    break;

                }
            }

            if (stop){
                break;
            }
            }
        }



//        while(true){
//            for (int i = 0; i < this.getUpperRight().x; i++) {
//                if(animalMap.containsKey(new Vector2d(i,y))){
//                    maxEmpty--;
//                }
//                if(maxEmpty<=0){
//                    yCheck = false;
//                }
//            }
//
//
//            int x = (int) ((Math.random() * ((this.getUpperRight().x- 1)))+1);
//
//            Vector2d newGrassPosition = new Vector2d(x,y);
//
//
//            if(!this.grassMap.containsKey(newGrassPosition)){
//                grassMap.put(newGrassPosition, new Plant(newGrassPosition));
//                //mapBoundary.place(newGrassPosition);
//                break;
//            }
//            if(yCheck){
//            y = (int) ((Math.random() * (this.getUpperRight().y -  1)) + 1);
//        }


        //spr pozycji
//        for (int y = centerY-2 ; y <= centerY+radiusY ; y++) {
//            for (int x = centerX - radiusX; x <= centerX + radiusX; x++) {
//
//                if(grassAmount > 0){
//                    grassAmount--;
//                    Vector2d newGrassPosition = new Vector2d(x,y);
//                    grassMap.put(newGrassPosition, new Plant(newGrassPosition));
//                    mapBoundary.place(newGrassPosition);
//                }else {mapBoundary.place(new Vector2d(x,y));}
//            }
//        }
   // }


    @Override
    public Object objectAt(Vector2d position){
        Object obj = this.animalMap.getOrDefault(position, null);;
        if (obj != null && !this.animalMap.get(position).isEmpty()) {
            return obj;
        }
        return grassMap.getOrDefault(position, null);

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public MapBoundary getMapBoundary() {
        return null;
    }

    ;

    @Override
    public Vector2d calculateLowerBound(){
        return this.getLowerLeft();
    }

    @Override
    public Vector2d calculateUpperBound(){
        return this.getUpperRight();
    }

    @Override
    public void place(Animal animal){
        super.place(animal);
       // mapBoundary.place(animal.getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        super.positionChanged(oldPosition, newPosition, animal);
    }

    public void consumptionAndReproduction(){
        ArrayList<Animal> consumptionList = new ArrayList<Animal>();
        ArrayList<ArrayList<Animal>> copulateList = new ArrayList<>();

        animalMap.forEach((key, value)->{
                if(grassMap.containsKey(key) && !value.isEmpty()){
                    consumptionList.add(value.get(0));
                }
                if(value.size() >= 2){
                    copulateList.add(value);
                }
        });
        for (Animal animal:
        consumptionList){

            if(this.removePlant(animal.getPosition())){
                animal.eat(PLANT_NUTRITIOUSNESS);
            }

        }

        for (ArrayList<Animal> animalList:
             copulateList) {//todo to można jakoś ładniej może?
            Reproduction reproduction = new Reproduction(animalList.get(0), animalList.get(1), this, energyDecrease);
            if (animalList.get(0).getEnergy() > reproduction.getENERGY_DECREASE() && animalList.get(1).getEnergy() > reproduction.getENERGY_DECREASE()){

            reproduction.reproduce();
            }
        }


    }

    private boolean removePlant(Vector2d plantToRemove){
        //tu działa

        if (grassMap.containsKey(plantToRemove))
        {

            grassMap.remove(plantToRemove);
            this.availableGrassSlots.add(plantToRemove);
            System.out.println("Ava" + availableGrassSlots);
            return true;
        }
return false;



    }
    public abstract Vector2d verifyMove(Vector2d oldVector, Vector2d newVector);

    public Statistics getStats(){
        Statistics statistics = new Statistics(this);

        return statistics;
    }


}
