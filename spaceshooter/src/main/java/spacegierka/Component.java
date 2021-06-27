package spacegierka;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public abstract class Component{

    private int healthPoints;
    private double speed;
    public Image image;
    public double width;
    public double height;
    public ImageView imageView;
    public Pane layer;


    //constructor
    Component(int hP, double speed, Pane layer){
        this.healthPoints = hP;
        this.speed = speed;
        this.layer = layer;
    }

    //getters
    public int getHealthPoints() {
        return healthPoints;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    //setters
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    //adding imageView to the class
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    //removing imageView to the class
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

}
