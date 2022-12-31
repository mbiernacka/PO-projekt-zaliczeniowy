package org.example;

import org.example.interfaces.IWorldMap;

import java.util.*;

public class Reproduction {
    public Animal parent1;
    public Animal parent2;
    private final IWorldMap map;
    private final Vector2d currentPosition;
    public  int ENERGY_DECREASE;

    public Reproduction(Animal parent1, Animal parent2, IWorldMap map, int ENERGY_DECREASE){
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.map = map;
        this.ENERGY_DECREASE = ENERGY_DECREASE;
        this.currentPosition = parent1.getPosition();
    }

    public int getENERGY_DECREASE() {
        return ENERGY_DECREASE;
    }

    public int[] calculateGenesDivision(){
        int genesNumber = this.parent1.getNUMBER_OF_GENES(); //tutaj pewnie bedzie stala do ilosci genow?
        int[] result = new int[2];
        int parent1Energy = this.parent1.getEnergy();
        int parent2Energy = this.parent2.getEnergy();
        double parent1Part = ((double)parent1Energy/((double)parent1Energy + (double)parent2Energy));
        result[0] = (int) Math.round(genesNumber * parent1Part);
        result[1] = genesNumber - result[0];
        return result;
    }

    public int[] drawSides(){
        // 1 - ozn. strona lewa; 2 - strona prawa
        int[] sides = new int[2];
        sides[0] = (int) ((Math.random() * (3-1)) +1);
        if(sides[0] == 1 ){
            sides[1] = 2;
        }
        else{
            sides[1] = 1;
        }
        return sides;
    }

    public Animal createChildAnimal(){
        int[] sides = drawSides();
        int[] genesDivision = calculateGenesDivision();
        Integer[] newChildGenes = new Integer[parent1.getNUMBER_OF_GENES()];
        Integer[] parent1Genes = this.parent1.getGenotype();
        Integer[] parent2Genes = this.parent2.getGenotype();

        //lewa strona pierwszego rodzica
        if(sides[0] == 1 && genesDivision[0] >= genesDivision[1] ){
            System.arraycopy(parent1Genes, 0, newChildGenes, 0,genesDivision[0]);
            System.arraycopy(parent2Genes, genesDivision[0], newChildGenes, genesDivision[0],genesDivision[1]);
        }
        //prawa strona pierwszego rodzica
        else if(sides[0] == 2 && genesDivision[0] >= genesDivision[1]){
            System.arraycopy(parent1Genes, parent1Genes.length - genesDivision[0], newChildGenes, 0,genesDivision[0]);
            System.arraycopy(parent2Genes, 0, newChildGenes, genesDivision[0],genesDivision[1]);
        }
        //lewa strona drugiego rodzica
        else if(sides[0] == 1){
            System.arraycopy(parent2Genes, 0, newChildGenes, 0,genesDivision[1]);
            System.arraycopy(parent1Genes, genesDivision[1], newChildGenes, genesDivision[1],genesDivision[0]);
        }
        //prawa strona drugiego rodzica
        else if(sides[0] == 2) {
            System.arraycopy(parent2Genes, parent2Genes.length - genesDivision[1], newChildGenes, 0,genesDivision[1]);
            System.arraycopy(parent1Genes, 0, newChildGenes, genesDivision[1],genesDivision[0]);
        }
        int NEW_CHILD_ENERGY = 2 * ENERGY_DECREASE;
        return new Animal(this.map, this.currentPosition, newChildGenes, NEW_CHILD_ENERGY, parent1.getNUMBER_OF_GENES());
    }

    public void mutation(Animal childAnimal) {
        Integer[] childGenes = childAnimal.getGenotype();
        Integer[] shuffledChildGenes = new Integer[parent1.NUMBER_OF_GENES];
        for (int i = 0; i <= parent1.NUMBER_OF_GENES-1; i++) {
            shuffledChildGenes[i] = i;
        }

        //ile genów ma sie zmienic
        int numberOfGenesToMutate = (int) ((Math.random() * ((childAnimal.getNUMBER_OF_GENES() + 1) - 1)) + 1);

        //shuffle genow w celu zmutowania losowych genow
        List<Integer> intList = Arrays.asList(shuffledChildGenes);
        Collections.shuffle(intList);
        intList.toArray(shuffledChildGenes);

        //random losowanie czy zwiekszyc czy zmniejszyc gen (wariant lekka korekta)
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(-1);
        //

        //mutacja
        for (int i = 0; i <= numberOfGenesToMutate - 1; i++) {
            Collections.shuffle(numbers);
            int choice = numbers.get(0);
            if (childGenes[shuffledChildGenes[i]] == 0 && choice == -1) {
                childGenes[shuffledChildGenes[i]] = 7;
            } else if (childGenes[shuffledChildGenes[i]] == 7 && choice == 1) {
                childGenes[shuffledChildGenes[i]] = 0;
            } else {
                childGenes[shuffledChildGenes[i]] += choice;
            }
        }
        childAnimal.mutate(childGenes);
    }

    public void reproduce(){
        Animal childAnimal = createChildAnimal();
        mutation(childAnimal);
        this.parent1.decreaseEnergy(ENERGY_DECREASE);
        this.parent1.increaseNumberOfKids();
        this.parent2.decreaseEnergy(ENERGY_DECREASE);
        this.parent2.increaseNumberOfKids();
    }

}
