package org.example.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Dashboard extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");
        TextField textField = new TextField();

        Button button = new Button("My Button");
        VBox menu = new VBox(textField, button);
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
