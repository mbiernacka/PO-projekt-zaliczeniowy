package org.example;

import org.example.boundary.BoundaryGlobe;
import org.example.boundary.BoundaryHellishPortal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
usunięcie martwych zwierząt z mapy,done
skręt i przemieszczenie każdego zwierzęcia,done
n*logn
< n*n
konsumpcja roślin na których pola weszły zwierzęta,
rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
wzrastanie nowych roślin na wybranych polach mapy.
 */
public class Engine {
    Engine(){

    }
    void run() throws IOException {
        int grassAmount = 10;
        BoundaryHellishPortal map = new BoundaryHellishPortal(grassAmount, new Vector2d(12,10));

        Animal animal2 = new Animal(map, new Vector2d(0, 9), new Integer[]{0, 0, 0, 0, 0, 0, 0}, 20);
   //    Animal animal1 = new Animal(map, new Vector2d(0, 9), new Integer[]{0, 2, 2, 5, 1, 4, 6}, 50);
//
//
//        Animal animal3 = new Animal(map, new Vector2d(3, 7), new Integer[]{1, 2, 3, 4, 5, 6, 7}, 20);
int i = 5;
        while(i>0) {
            i--;
            //System.out.println(map);
            map.removeDeadAnimalAndMove();
            map.sortAnimalMap();
            map.consumptionAndReproduction();
           // map.createPlant();
            System.out.println(map);
           System.out.println(map.getStats().toCsv());
            //TimeUnit.MINUTES.sleep(1);




            //
            // spawn roślin

        }
    }
}

