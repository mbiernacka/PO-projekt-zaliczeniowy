package org.example;
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
    void run(){
        AbstractWorldMap map = new BasicMap(10, new Vector2d(12,10));

        Animal animal2 = new Animal(map, new Vector2d(5, 5), new Integer[]{0, 2, 3, 4, 5, 6, 7}, 20);
        Animal animal1 = new Animal(map, new Vector2d(3, 7), new Integer[]{0, 2, 2, 5, 1, 4, 6}, 50);


        Animal animal3 = new Animal(map, new Vector2d(3, 7), new Integer[]{1, 2, 3, 4, 5, 6, 7}, 0);

       // while(true) {
            System.out.println(map);
            map.removeDeadAnimalAndMove();
            System.out.println(map);
            map.sortAnimalMap();
            // przejście jedzenia i rozmnażania
            // spawn roślin

       // }
    }
}

