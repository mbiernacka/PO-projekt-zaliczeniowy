package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parameters {
    private ArrayList paramList = new ArrayList();
    public Parameters(String path) throws FileNotFoundException {

        //parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            paramList.add(sc.next());  //find and returns the next complete token from this scanner
        }
        sc.close();
        if (paramList.size() != 14){
          //  throw new InputMismatchException();
        }

    }

    public ArrayList getParamList() {
        return new ArrayList<>(this.paramList);
    }
}
