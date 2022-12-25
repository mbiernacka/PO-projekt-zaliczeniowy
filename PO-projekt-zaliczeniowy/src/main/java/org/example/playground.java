package org.example;
// temp class dodana na testy

public class playground {

    public static void main(String[] args) {
    int centerX = 10;
    int centerY = 15;
    int radius = 5;

for (int y = centerY - radius; y <= centerY + radius; y++) {
        for (int x = centerX - radius; x <= centerX + radius; x++) {

        System.out.println("(" + x + ", " + y + ")");
        }
        }

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



 */