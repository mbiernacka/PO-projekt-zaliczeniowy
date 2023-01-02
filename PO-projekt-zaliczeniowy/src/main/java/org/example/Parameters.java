package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parameters {
    private final ArrayList<Integer> paramList = new ArrayList<Integer>();
    public Parameters(String path) throws FileNotFoundException {

       try{

        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");
        while (sc.hasNext())
        {
            Integer current = Integer.valueOf(sc.next());
            if (current < 0){
                throw new IllegalArgumentException();

            }
            paramList.add(current);
        }
        sc.close();
        if (paramList.size() != 11){

            throw new InputMismatchException();

        }}catch(FileNotFoundException e){
        e.printStackTrace();
           //System.exit(1);
    }
       catch (IllegalArgumentException e){
           e.printStackTrace();
           System.exit(1);
       }
    try {
        if (paramList.get(0) == 0 || paramList.get(1) ==0){
            throw new InputMismatchException();
        }
    }catch (InputMismatchException e){
        e.printStackTrace();
        System.exit(1);
    }


    }

    public ArrayList<Integer> getParamList() {
        return new ArrayList<Integer>(this.paramList);
    }
}
