package org.example;

import org.example.boundary.BoundaryGlobe;
import org.example.boundary.BoundaryHellishPortal;
import org.example.interfaces.IAppObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class Engine implements Runnable {
    private final List<IAppObserver> observerList;
    private int moveDelay;
    BasicMap map;
    Vector2d[] positions;
   public Engine(BasicMap map, Vector2d[] positions){
        this.observerList = new ArrayList<>();
        this.map = map;
        this.positions = positions;
    }
    public void run() {
//        for (Vector2d v:
//             this.positions) {
//            new Animal(map, v, new Integer[]{0, 0, 0, 0, 0, 0, 0}, 20);
//
//        }
        new Animal(map, new Vector2d(0,0), new Integer[]{0, 0, 0, 0, 0, 0, 0}, 20);
        new Animal(map, new Vector2d(5,5), new Integer[]{0, 1, 2, 3, 4, 5, 6}, 20);
        new Animal(map, new Vector2d(8,2), new Integer[]{4, 2, 3, 7, 5, 6, 6}, 20);
        new Animal(map, new Vector2d(8,2), new Integer[]{4, 2, 3, 7, 5, 6, 6}, 20);
        int i = 20;
        while(i>0) {
            i--;
            map.removeDeadAnimalAndMove();
            this.notifyObservers();
            map.sortAnimalMap();
            map.consumptionAndReproduction();
            map.createPlant();
            System.out.println(map);
            System.out.println(map.getStats().toCsv());


            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addObserver(IAppObserver observer){
        this.observerList.add(observer);
    }
    public void setDelay(int delay) {
        moveDelay = delay;
    }
    public void notifyObservers(){
        for (IAppObserver observer: this.observerList){
            observer.positionAppChanged();
        }
    }

}

