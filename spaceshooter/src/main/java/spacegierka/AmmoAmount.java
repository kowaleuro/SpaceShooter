package spacegierka;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AmmoAmount {
    public Label label = new Label("10");
    private Pane layer;

    AmmoAmount(Pane layer){
        this.layer = layer;
        this.addToLayer();
        relocation(550,120);
        Font font = new Font("Arial", 18);
        label.setFont(font);
        label.setTextFill(Color.GREEN);

    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    //adding imageView to the class
    public void addToLayer() {
        this.layer.getChildren().add(this.label);
    }

    //removing imageView to the class
    public void removeFromLayer() {
        this.layer.getChildren().remove(this.label);
    }

    public void relocation(int x, int y){
        this.label.relocate(x,y);
    }

    public void change(int amountOfAmmo){
        this.label.setText("ammo: " + (String.valueOf(10 - amountOfAmmo)));
    }
}
