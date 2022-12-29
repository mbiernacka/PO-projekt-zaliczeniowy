package org.example.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.*;
import org.example.boundary.BoundaryGlobe;
import org.example.boundary.BoundaryHellishPortal;
import org.example.interfaces.IAppObserver;
import org.example.interfaces.IMapElement;

import java.io.FileNotFoundException;
import java.util.*;

public class App extends Application implements IAppObserver {

    private BasicMap map;
    private final GridPane gridPane = new GridPane();
    private final int CONSTRAINTS = 55;
    private final int SQUARESIZE = 50;
    private Engine engine;
    private final static int moveDelay = 1500;

    public void start(Stage primaryStage) throws FileNotFoundException {

        TextField moves = new TextField();
        Button start = new Button("Start");
        VBox top = new VBox(moves, start);
        VBox main = new VBox(top, gridPane);
        HBox body = new HBox(main);

        start.setOnAction(click -> {
            Thread thread = new Thread(engine);
            thread.start();
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
                                    GUIElementBox element = new GUIElementBox(o);
                                    gridPane.add(element.getvBox(), p, w);
                                    GridPane.setHalignment(element.getvBox(), HPos.CENTER);
                                    GridPane.setHalignment(element.getvBox(), HPos.CENTER);
                                }
                            }
                        }else {
                            IMapElement object = (IMapElement) this.map.objectAt(new Vector2d(j, i));
                            if (object != null) {
                                GUIElementBox element = new GUIElementBox(object);
                                gridPane.add(element.getvBox(), p, w);
                                GridPane.setHalignment(element.getvBox(), HPos.CENTER);
                                GridPane.setHalignment(element.getvBox(), HPos.CENTER);
                            }
                        }


                    }
                    p++;
                }
                w++;
            }
        }

    public void init(String path) throws FileNotFoundException {
        //wczytwana
        org.example.Parameters parameters = new org.example.Parameters(path);
        this.map = new BoundaryGlobe(10, new Vector2d(12,10));
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(5,7)};


        this.engine = new Engine(map, positions);
        engine.setDelay(moveDelay);
        engine.addObserver(this);

    }
    public void positionAppChanged(){
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            try {
                newGridPane();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
