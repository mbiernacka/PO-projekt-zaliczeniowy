package org.example;

import org.example.interfaces.IWorldMap;

public class Main {
    public static void main(String[] args) {

        AbstractWorldMap map = new BasicMap(10, new MapBoundary(new Vector2d(12,10)));

        Animal animal1 = new Animal(map, new Vector2d(2, 2));

        System.out.println(map);
        animal1.move(2);
        System.out.println(map);
        animal1.move(2);
        System.out.println(map);
        animal1.move(3);
        System.out.println(map);
       // animal1.move(5);

//        map.place(new Animal(map, new Vector2d(8,1)));
//        map.place(new Animal(map, new Vector2d(0,14)));
//        map.place(new Animal(map, new Vector2d(20,0)));



    }
}