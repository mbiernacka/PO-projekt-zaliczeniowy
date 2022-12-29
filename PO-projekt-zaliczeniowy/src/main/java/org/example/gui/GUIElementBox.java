package org.example.gui;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.interfaces.IMapElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIElementBox {

    static final int VIEW_SIZE = 30;
    private final VBox vBox;

    public GUIElementBox(IMapElement element) throws FileNotFoundException {
        this.vBox = new VBox();
        Image image = new Image(new FileInputStream(element.getTexture()));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(VIEW_SIZE);
        imageView.setFitWidth(VIEW_SIZE);
        Label label = new Label(element.getLabel());
        this.vBox.getChildren().setAll(imageView, label);
        this.vBox.setAlignment(Pos.CENTER);
    }

    public VBox getvBox(){
        return this.vBox;
    }

}
