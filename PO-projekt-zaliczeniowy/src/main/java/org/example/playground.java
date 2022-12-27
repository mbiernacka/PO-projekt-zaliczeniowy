package org.example;
// temp class dodana na testy

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class playground {


    public static void main(String[] args){
        HashMap<String, ArrayList<String>> animalMap = new HashMap<>();
        if(animalMap.get("a") != null){
            ArrayList<String> ar = animalMap.get("a");
            ar.add("test");

        }else {
            animalMap.put("a", new ArrayList<>());

        }
        System.out.println(animalMap);
        System.out.println(animalMap.get("a"));
    }
}
/*
ocena:
- czy działa
- czy ma wszystkie funkcje
- archutektura
- clean code
-obsługa błędów
-mile widziane:
testy
...
granice 2 i jedna abstrakcyjna
interface do mab boundary




Mapa:
Ustalaie granic
Co się dzieje na granicy(interface)
Pola 80/20 działało xd/done


rand- działa ale jak doda się animal to animal może nadpisać


// map boundary move ver
 */