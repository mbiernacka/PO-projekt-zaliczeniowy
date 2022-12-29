package org.example;
// temp class dodana na testy

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
class MyApplication extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    }

public class playground  {


    public static void main(String[] args) {
        //Application.launch(MyApplication.class, args);
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