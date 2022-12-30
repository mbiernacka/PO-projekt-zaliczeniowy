package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Statistics {
    private int animalsCounter=0, plantsCounter, emptyFieldsCounter=0;
    //todo licznik genotypu
    private float avEnergy=0, avLifespan=0;
    BasicMap map;

    public Statistics(BasicMap map){
        this.map = map;
        //animal C
        Map<Vector2d, ArrayList<Animal>> animalMap = map.getAnimalMap();
        animalMap.forEach((key, value)->{
            animalsCounter += value.size();
        });
        //grassC
        plantsCounter = map.getGrassMap().size();
        //emptyfield
        for(int i=0;i<map.calculateUpperBound().y+1;++i){
            for(int j=0;j<map.calculateUpperBound().x+1;++j){
                if (!map.isOccupied(new Vector2d(j,i))){
                    ++emptyFieldsCounter;
                }
            }
        }
        //
//        int enegrySum=0, ageSum = 0;
//        Iterator<Map.Entry<Vector2d, ArrayList<Animal>>> mapIterator = animalMap.entrySet().iterator();
//        while (mapIterator.hasNext()){
//            Map.Entry<Vector2d, ArrayList<Animal>> entry = mapIterator.next();
//
//        }
        int enegrySum= animalMap.values().stream().mapToInt(l -> l.stream().mapToInt(a->a.getEnergy()).sum()).sum();
        int ageSum= animalMap.values().stream().mapToInt(l -> l.stream().mapToInt(a->a.getAge()).sum()).sum();
        if(animalsCounter>0){
            avEnergy = enegrySum / animalsCounter;
            avLifespan = ageSum / animalsCounter;
        }


    }

    public void whenWriteStringUsingBufferedWritter_thenCorrect()
        //
            throws IOException {
        String str = "Hello123";
        BufferedWriter writer = new BufferedWriter(new FileWriter("test"));
        writer.write(str);

        writer.close();
    }
    public String toCsv(){
            return String.format("%d;%d;%d;%.2f;%.2f",animalsCounter,
                    plantsCounter, emptyFieldsCounter, avEnergy, avLifespan);
    }
    @Override
    public String toString() {
        return String.format("number of animals: %d\n number of plants: %d\n empty fields: %d \naverage energy %.2f\naverage lifespan: %.2f",animalsCounter,
                plantsCounter, emptyFieldsCounter, avEnergy, avLifespan);
    }
}
