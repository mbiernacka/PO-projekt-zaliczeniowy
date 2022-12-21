package org.example;

import org.example.interfaces.IWorldMap;

public class Main {
    public static void main(String[] args) {

        AbstractWorldMap map = new BasicMap(10, new MapBoundary());
        System.out.println(map);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        animal1.move(3);
        animal1.move(4);
        map.place(new Animal(map, new Vector2d(8,1)));
        map.place(new Animal(map, new Vector2d(0,14)));
        map.place(new Animal(map, new Vector2d(20,0)));
        System.out.println(map);

    }
}