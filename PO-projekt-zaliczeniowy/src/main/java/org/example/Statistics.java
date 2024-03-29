package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Statistics {
    private int animalsCounter=0;
    private final int plantsCounter;
    private int emptyFieldsCounter=0;
    //todo licznik genotypu
    private float avEnergy=0, avLifespan=0;
    private final String mostCommonGenotype;
    private final Integer mostCommonGenotypeCounter;
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
        int enegrySum= animalMap.values().stream().mapToInt(l -> l.stream().mapToInt(a->a.getEnergy()).sum()).sum();
        int ageSum= animalMap.values().stream().mapToInt(l -> l.stream().mapToInt(a->a.getAge()).sum()).sum();
        if(animalsCounter>0){
            avEnergy = enegrySum / animalsCounter;
            avLifespan = ageSum / animalsCounter;
        }

        Map<String, Integer> genotypeCounter=  this.map.printGenotypeCounter();
        Map<String, Integer> sorted = genotypeCounter.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        Map.Entry<String,Integer> entry = sorted.entrySet().iterator().next();
        mostCommonGenotype = entry.getKey();
        mostCommonGenotypeCounter = entry.getValue();
    }

    public String toCsv(){
            return String.format("%d;%d;%d;%.2f;%.2f;%s",animalsCounter,
                    plantsCounter, emptyFieldsCounter, avEnergy, avLifespan, mostCommonGenotype);
    }
    @Override
    public String toString() {
        return String.format("""
                        number of animals: %d
                        number of plants: %d
                        empty fields: %d
                        average energy %.2f
                        average lifespan: %.2f
                        most common genotype: %s - %d occurances""",animalsCounter,
                plantsCounter, emptyFieldsCounter, avEnergy, avLifespan, mostCommonGenotype, mostCommonGenotypeCounter);
    }
}
