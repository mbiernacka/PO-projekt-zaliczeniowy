package org.example.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.*;
import org.example.boundary.BoundaryGlobe;
import org.example.boundary.BoundaryHellishPortal;
import org.example.interfaces.IAppObserver;
import org.example.interfaces.IMapElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class App extends Application implements IAppObserver {

    private BasicMap map;
    private final GridPane gridPane = new GridPane();
    private final int CONSTRAINTS = 55;
    private final int SQUARESIZE = 50;
    private Engine engine;
    private final static int moveDelay = 750;

    //GUIElementBox element = new GUIElementBox(new Animal());
   private Label statsAnimal;


    public void start(Stage primaryStage) throws FileNotFoundException {

        TextField moves = new TextField();
        Button start = new Button("Start");
        Button pause = new Button("Pause");
        Button resume = new Button("Resume");
        Label stats = new Label("");
        statsAnimal = new Label("");
        statsAnimal.setVisible(false);
        stats.setVisible(false);
        pause.setVisible(false);
        resume.setVisible(false);
        VBox top = new VBox(moves, start,pause,resume,stats,statsAnimal);
        VBox main = new VBox(top, gridPane);
        HBox body = new HBox(main);
        //Thread thread;
        Thread thread = new Thread(engine);

        start.setOnAction(click -> {
            pause.setVisible(true);

            start.setVisible(false);
            thread.start();
        });

        pause.setOnAction(click -> {
            try {
                pause.setVisible(false);
                stats.setText(map.getStats().toString());
               resume.setVisible(true);
                stats.setVisible(true);
                thread.suspend();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        resume.setOnAction(click -> {
            pause.setVisible(true);
            resume.setVisible(false);
            thread.resume();
        });
        primaryStage.setOnCloseRequest(event -> {
            thread.stop();
        });
        newGridPane();
        Scene scene = new Scene(body, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void newGridPane() throws FileNotFoundException {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();

        Vector2d lowerBound = map.calculateLowerBound();
        Vector2d upperBound = map.calculateUpperBound();

        gridPane.setGridLinesVisible(true);

        drawXLabel(lowerBound, upperBound, gridPane);
        drawYLabel(lowerBound, upperBound, gridPane);
        drawXYLabel(gridPane);
        drawObjects(lowerBound, upperBound, gridPane);
    }

    public void drawYLabel(Vector2d lowerBound, Vector2d upperBound, GridPane gridPane) {
        int m = 1;
        for (int y = upperBound.y; y >= lowerBound.y; y--) {
            gridPane.getRowConstraints().add(new RowConstraints(CONSTRAINTS));
            Label label = new Label(Integer.toString(y));
            label.setMinHeight(SQUARESIZE);
            label.setMinWidth(SQUARESIZE);
            label.setAlignment(Pos.CENTER);
            gridPane.add(label, 0, m, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
            m++;
        }
    }

        public void drawXLabel(Vector2d lowerBound, Vector2d upperBound, GridPane gridPane) {
            int n = 1;
            for (int x = lowerBound.x; x <= upperBound.x; x++) {
                gridPane.getColumnConstraints().add(new ColumnConstraints(CONSTRAINTS));
                Label label = new Label(Integer.toString(x));
                label.setMinHeight(SQUARESIZE);
                label.setMinWidth(SQUARESIZE);
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, n, 0, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
                n++;
            }
        }

        public void drawXYLabel( GridPane gridPane) {
            Label labelXY = new Label("y/x");
            gridPane.add(labelXY, 0, 0);
            GridPane.setHalignment(labelXY, HPos.CENTER);
        }
Animal trackedAnimal;
    Boolean isTracked = false;
        public void drawObjects(Vector2d lowerBound, Vector2d upperBound, GridPane gridPane) throws FileNotFoundException {
            int w = 1;
            for (int i = upperBound.y; i >= lowerBound.y; i--) {
                int p = 1;
                for (int j = lowerBound.x; j <= upperBound.x; j++) {
                    if (this.map.isOccupied(new Vector2d(j, i))) {
                        if (this.map.objectAt(new Vector2d(j, i)) instanceof List){
                            ArrayList<IMapElement> list = (ArrayList<IMapElement>) this.map.objectAt(new Vector2d(j, i));
                            for (IMapElement o:
                                 list) {
                                if (o != null) {
                                    Button element = new Button(o.toString());

                                    element.setOnAction(click -> {
                                            isTracked = true;
                                            trackedAnimal = (Animal) o;
                                        element.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(100,37,245), CornerRadii.EMPTY, Insets.EMPTY)));
                                });
                                    if(isTracked && trackedAnimal.equals((Animal)o)){
                                        element.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(100,37,245), CornerRadii.EMPTY, Insets.EMPTY)));

                                    }else {
                                        element.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(205, 37, 245), CornerRadii.EMPTY, Insets.EMPTY)));

                                    }
                                    element.setMinHeight(50);
                                    element.setMinWidth(50);
                                    element.setFont(new Font(10));

                                    element.setAlignment(Pos.CENTER);
                                    GridPane.setHalignment(element, HPos.CENTER);
                                    gridPane.add(element,p,w);
                                }
                            }
                        }else {
                            IMapElement object = (IMapElement) this.map.objectAt(new Vector2d(j, i));
                            if (object != null) {
                                Label element = new Label(object.toString());
                                element.setMinHeight(50);
                                element.setMinWidth(50);
                                element.setFont(new Font(10));
                                element.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.rgb(8,238,8), CornerRadii.EMPTY, Insets.EMPTY)));
                                element.setAlignment(Pos.CENTER);
                                GridPane.setHalignment(element, HPos.CENTER);
                                gridPane.add(element,p,w);

                            }
                        }


                    }
                    p++;
                }
                w++;
            }
            gridPane.getRowConstraints().add(new RowConstraints(CONSTRAINTS));
            gridPane.getColumnConstraints().add(new ColumnConstraints(CONSTRAINTS));
        }

    public void init(String path) throws FileNotFoundException {
        //wczytwana
        final org.example.Parameters parameters = new org.example.Parameters(path);
       if (parameters.getParamList().get(2)==0) {
           this.map = new BoundaryGlobe(parameters.getParamList().get(3), new Vector2d(parameters.getParamList().get(0), parameters.getParamList().get(1)), parameters.getParamList().get(4), parameters.getParamList().get(8));
       }else {
           this.map = new BoundaryHellishPortal(parameters.getParamList().get(3), new Vector2d(parameters.getParamList().get(0), parameters.getParamList().get(1)),parameters.getParamList().get(4),parameters.getParamList().get(8));

       }

        //1.csv 2.csv
        int i = 0;
        File file;
       while (true){
       try {
           file = new File(String.format("%d.csv", i));
            i++;
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                break;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
       }

    this.engine = new Engine(map, parameters, file);
    engine.setDelay(moveDelay);
    engine.addObserver(this);


    }
    public void positionAppChanged(){
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            try {
                newGridPane();
if (isTracked){

    this.statsAnimal.setText(trackedAnimal.trackedAnimalStats());
    this.statsAnimal.setVisible(true);
}
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
