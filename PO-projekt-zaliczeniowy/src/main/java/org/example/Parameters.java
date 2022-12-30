package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parameters {
    private ArrayList<Integer> paramList = new ArrayList<Integer>();
    public Parameters(String path) throws FileNotFoundException {

       try{
        //parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            paramList.add( Integer.valueOf(sc.next()));  //find and returns the next complete token from this scanner
        }
        sc.close();
        if (paramList.size() != 14){
            //todo dodać ograniczenie
          //  throw new InputMismatchException();
        }}catch(FileNotFoundException e){
        e.printStackTrace();
    }


    }

    public ArrayList<Integer> getParamList() {
        return new ArrayList<Integer>(this.paramList);
    }
}
