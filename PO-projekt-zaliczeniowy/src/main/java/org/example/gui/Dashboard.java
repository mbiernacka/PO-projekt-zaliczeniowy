package org.example.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Dashboard extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Load parameters");

        TextField textField = new TextField();
        textField.setPromptText("Enter path to your own configuration");
        Button exConfig1  = new Button("Start Pre-build Simulation 1");
        Button exConfig2  = new Button("Start Pre-build Simulation 2");

        Button button = new Button("Start Custom Simulation");
        VBox menu = new VBox(exConfig1,exConfig2,textField, button);

        exConfig1.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            App app = new App();
                            app.init("src\\main\\resources\\gameSetting.csv");
                            app.start(new Stage());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }


        });
        exConfig2.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            App app = new App();
                            app.init("src\\main\\resources\\gameSetting2.csv");
                            app.start(new Stage());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }


        });

        button.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                    try {
                            App app = new App();
                            app.init(textField.getText());
                            app.start(new Stage());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                    }
                    }
                });
            }


        });

        Scene scene = new Scene(menu, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
