package spacegierka;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static javax.swing.text.StyleConstants.Bold;
import static javax.swing.text.StyleConstants.Italic;

public class Score {

    public Label label = new Label("0");
    private Pane layer;

    Score(Pane layer){
        this.layer = layer;
        this.addToLayer();
        relocation(550,80);
        Font font = new Font("Arial", 18);
        label.setFont(font);
        label.setTextFill(Color.RED);

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

    public void increase(int score){
        this.label.setText("score: " + (String.valueOf(score)));
    }
}
