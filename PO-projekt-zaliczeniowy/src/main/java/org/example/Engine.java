package org.example;

import org.example.boundary.BoundaryGlobe;
import org.example.boundary.BoundaryHellishPortal;
import org.example.interfaces.IAppObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Parameters parameters;
File file;

   public Engine(BasicMap map, Parameters parameters, File file){
        this.observerList = new ArrayList<>();
        this.map = map;
        this.parameters = parameters;
        this.file = file;
   }
    public void run() {
        for (int i =0; i< parameters.getParamList().get(6);++i) {
            Vector2d vector2d = new Vector2d(((int)(Math.random()* 1000)% map.getUpperRight().x),((int)(Math.random()* 1000))% map.getUpperRight().y);
            new Animal(map, vector2d, parameters.getParamList().get(7), parameters.getParamList().get(10));
        }
       // new Animal(map, new Vector2d(0,0), new Integer[]{0, 0, 0, 0, 0, 0, 0}, parameters.getParamList().get(7), parameters.getParamList().get(10));
//        new Animal(map, new Vector2d(5,5), new Integer[]{0, 1, 2, 3, 4, 5, 6}, (Integer) parameters.getParamList().get(7),(Integer)parameters.getParamList().get(10));
//        new Animal(map, new Vector2d(8,2), new Integer[]{4, 2, 3, 7, 5, 6, 6}, (Integer) parameters.getParamList().get(7),(Integer)parameters.getParamList().get(10));
//        new Animal(map, new Vector2d(8,2), new Integer[]{4, 2, 7, 8, 9, 4, 6}, (Integer) parameters.getParamList().get(7),(Integer)parameters.getParamList().get(10));
        int i = 20;
        while(true) {
            i--;
            map.removeDeadAnimalAndMove();
            this.notifyObservers();
            map.sortAnimalMap();
            map.consumptionAndReproduction();
            map.createPlant();
            map.printGenotypeCounter();
            System.out.println(map);
            try {
                whenAppendToFileUsingFileWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(map.getStats().toCsv());


            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void whenAppendToFileUsingFileWriter()
            throws IOException {

        FileWriter fw = new FileWriter(this.file.getPath(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(map.getStats().toCsv());
        bw.newLine();
        bw.close();

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

