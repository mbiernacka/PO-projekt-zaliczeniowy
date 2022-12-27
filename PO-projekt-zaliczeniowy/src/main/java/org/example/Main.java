package org.example;
import org.example.boundary.BoundaryHellishPortal;
import org.example.boundary.MapBoundary;
import org.example.interfaces.IWorldMap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        AbstractWorldMap map = new BasicMap(10, new BoundaryHellishPortal(new Vector2d(12,10)));

        Animal animal1 = new Animal(map, new Vector2d(3, 7), new Integer[]{7, 2, 2, 5, 1, 4, 6}, 50);
        //Animal animal2 = new Animal(map, new Vector2d(2, 2), new Integer[]{1, 2, 3, 4, 5, 6, 7}, 20);

        map.place(animal1);
        System.out.println(map);
        //map.place(animal2);
        animal1.move(0);
        animal1.move(0);
        animal1.move(0);
        System.out.println(map);
        animal1.move(0);
        System.out.println(map);
       // Reproduction reproduction = new Reproduction(animal1, animal2, map, animal1.getPosition());
       // reproduction.reproduce();
        //reproduction.mutation(reproduction.createChildAnimal());
        //System.out.println(map);
    }
}