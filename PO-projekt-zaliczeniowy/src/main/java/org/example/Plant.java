package org.example;

import org.example.interfaces.IMapElement;

public class Plant implements IMapElement {
    private final Vector2d position;

    public Plant(Vector2d position) {
        this.position = position;
    }

    @Override
    public String getTexture() {
        return "src/main/resources/grass.png";
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String getLabel() {
        return "Plant";
    }

    @Override
    public String toString(){
        return "*";
    }
}
