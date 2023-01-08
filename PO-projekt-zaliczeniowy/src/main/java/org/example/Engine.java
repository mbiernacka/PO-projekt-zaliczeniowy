package org.example;

import org.example.interfaces.IAppObserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Engine implements Runnable {
    private final List<IAppObserver> observerList;
    private int moveDelay;
    private BasicMap map;
    private final Parameters parameters;
    private File file;

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
        while(true) {
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

