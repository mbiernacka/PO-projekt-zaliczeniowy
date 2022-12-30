package org.example;

import javafx.application.Application;
import org.example.gui.App;
import org.example.gui.Dashboard;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //todo generacja roślin

//        AbstractWorldMap map = new BasicMap(10, new Vector2d(12,10));
//
//        Animal animal2 = new Animal(map, new Vector2d(3, 7), new Integer[]{1, 2, 3, 4, 5, 6, 7}, 20);
//        Animal animal1 = new Animal(map, new Vector2d(3, 7), new Integer[]{7, 2, 2, 5, 1, 4, 6}, 50);
//
//
//        Animal animal3 = new Animal(map, new Vector2d(3, 7), new Integer[]{1, 2, 3, 4, 5, 6, 7}, 0);
//
//        System.out.println(map.getAnimalMap());
//       map.sortAnimalMap();
//        System.out.println(map.getAnimalMap());
//Engine engine = new Engine();
//engine.run();
        Application.launch(Dashboard.class,args);
        //Application.launch(App.class, args);
//       animal1.move(0);
//        animal2.move(4);
//        animal1.move(0);
//        map.removeDeadAnimal();
//        System.out.println(map);

// poza mapą        animal1.move(0);
//        System.out.println(map);
       // Reproduction reproduction = new Reproduction(animal1, animal2, map, animal1.getPosition());
       // reproduction.reproduce();
        //reproduction.mutation(reproduction.createChildAnimal());
        //System.out.println(map);
    }
}