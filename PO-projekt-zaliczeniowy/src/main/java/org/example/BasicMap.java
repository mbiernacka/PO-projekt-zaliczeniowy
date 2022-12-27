package org.example;

import org.example.boundary.MapBoundary;

import javax.swing.table.TableRowSorter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BasicMap extends AbstractWorldMap {
    private int grassAmount;
    private HashMap<Vector2d, Plant> grassMap;

    private  Vector2d size;
//new v2

    public BasicMap(int grassAmount, Vector2d size){
        this.grassAmount =grassAmount;
        this.grassMap = new HashMap<>();
        this.size = size;


        for (int i = 0; i < grassAmount; i++) {

           if((int)(1+Math.random() * 10) <8){
                centerPlant();//losowa pozycja z centralnego
           }else{
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

        int min = 0;
        int maxY = this.getUpperRight().x-1;
        int maxX = this.getUpperRight().y-1;
        int repeats = maxY*maxY;
        while(true){
            int x = (int) ((Math.random() * (maxX -1)) + 1);
            int y = (int) ((Math.random() * (maxY -  1)) + 1);
            Vector2d newGrassPosition = new Vector2d(x,y);


            if(!this.grassMap.containsKey(newGrassPosition)){
                grassMap.put(newGrassPosition, new Plant(newGrassPosition));
                //mapBoundary.place(newGrassPosition);
                break;
            }
        }

    }

    protected void centerPlant(){

        int centerX = this.getUpperRight().x /2;
        int centerY = this.getUpperRight().y /2;
        int radiusX =centerX;
        int y = centerY;
        int radiusY = 0;
        //check center
        int maxEmpty = this.getUpperRight().x;
        boolean yCheck = true;

        while(true){
            for (int i = 0; i < this.getUpperRight().x; i++) {
                if(animalMap.containsKey(new Vector2d(i,y))){
                    maxEmpty--;
                }
                if(maxEmpty<=0){
                    yCheck = false;
                }
            }


            int x = (int) ((Math.random() * ((this.getUpperRight().x- 1)))+1);

            Vector2d newGrassPosition = new Vector2d(x,y);


            if(!this.grassMap.containsKey(newGrassPosition)){
                grassMap.put(newGrassPosition, new Plant(newGrassPosition));
                //mapBoundary.place(newGrassPosition);
                break;
            }
            if(yCheck){
            y = (int) ((Math.random() * (this.getUpperRight().y -  1)) + 1);
        }
        }

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
    }


    @Override
    public Object objectAt(Vector2d position){
        Object obj = super.objectAt(position);
        if (obj instanceof ArrayList<?>) {
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


}
