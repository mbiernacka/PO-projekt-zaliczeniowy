package org.example;

import java.lang.Math;
import java.util.HashMap;

public class BasicMap extends AbstractWorldMap {
    private HashMap<Vector2d, Plant> grassMap;
    public final MapBoundary mapBoundary;
//old
//    public BasicMap(int grassAmount){
//        this.grassMap = new HashMap<>();
//        this.mapBoundary = new MapBoundary();
//        int min = 0;
//        int max = (int)(Math.sqrt(10*grassAmount));
//
//        for (int i = 0; i < grassAmount; i++){
//            int x = (int) ((Math.random() * (max - min + 1)) + min);
//            int y = (int) ((Math.random() * (max - min + 1)) + min);
//            Vector2d newGrassPosition = new Vector2d(x,y);
//
//            int repeats = 0;
//            if(this.grassMap.containsKey(newGrassPosition)){
//                i--;
//                repeats++;
//            }
//            if(repeats == 0){
//                grassMap.put(newGrassPosition, new Plant(newGrassPosition));
//                mapBoundary.place(newGrassPosition);
//            }
//        }
//    }
//new v1
//    public BasicMap(int grassAmount){
//        this.grassMap = new HashMap<>();
//        this.mapBoundary = new MapBoundary();
//        int min = 0;
//        int max = (int)(Math.sqrt(10*grassAmount));
//
//        for (int i = 0; i < grassAmount; i++){
//            int x,y;
//            for (int k = mapBoundary.getUpperRight().x/2; k < mapBoundary.getUpperRight().x; k++) {
//                y =k;
//
//
//            for (int j = mapBoundary.getUpperRight().x/2; j < mapBoundary.getUpperRight().x; j++) {
//                x=  j%2==0 ? j : -j;
//                Vector2d newGrassPosition = new Vector2d(x,y);
//
//                int repeats = 0;
//                if(this.grassMap.containsKey(newGrassPosition)){
//                    i--;
//                    repeats++;
//                }
//                if(repeats == 0){
//                    grassMap.put(newGrassPosition, new Plant(newGrassPosition));
//                    mapBoundary.place(newGrassPosition);
//                }
//                x=  j%2==0 ? -j : j;
//                // to będzie można ładniej zrobic
//                 newGrassPosition = new Vector2d(x,y);
//
//                 repeats = 0;
//                if(this.grassMap.containsKey(newGrassPosition)){
//                    i--;
//                    repeats++;
//                }
//                if(repeats == 0){
//                    grassMap.put(newGrassPosition, new Plant(newGrassPosition));
//                    mapBoundary.place(newGrassPosition);
//                }
//
//            }
//            }
//        }
//    }
//new v2
    public BasicMap(int grassAmount){
        this.grassMap = new HashMap<>();
        this.mapBoundary = new MapBoundary();

        int centerX = 5;
        int centerY = 5;
        int radius = 5;

        for (int y = centerY - radius; y <= centerY + radius; y++) {
            for (int x = centerX - radius; x <= centerX + radius; x++) {

                if(grassAmount > 0){
                    grassAmount--;
                Vector2d newGrassPosition = new Vector2d(x,y);
                grassMap.put(newGrassPosition, new Plant(newGrassPosition));
                mapBoundary.place(newGrassPosition);
                }
            }
        }


//        for (int i = 0; i < grassAmount; i++){
//            int x = (int) ((Math.random() * (max - min + 1)) + min);
//            int y = (int) ((Math.random() * (max - min + 1)) + min);
//            ;
//
//            int repeats = 0;
//            if(this.grassMap.containsKey(newGrassPosition)){
//                i--;
//                repeats++;
//            }



        }



    @Override
    public Object objectAt(Vector2d position){
        Object obj = super.objectAt(position);
        if (obj instanceof Animal) {
            return obj;
        }
        return grassMap.getOrDefault(position, null);

    };

    @Override
    public Vector2d calculateLowerBound(){
        return this.mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d calculateUpperBound(){
        return this.mapBoundary.getUpperRight();
    }

    @Override
    public void place(Animal animal){
        super.place(animal);
        mapBoundary.place(animal.getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition, newPosition);
        this.mapBoundary.positionChanged(oldPosition,newPosition);
    }
}
