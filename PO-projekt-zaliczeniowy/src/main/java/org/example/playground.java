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
