package spacegierka;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Spaceship extends Component{

    //starting parameters
    private double x;
    private double y = 700;
    private double dx = 9;
    private double dy = 3;

    //constructor
    Spaceship(int hP, double speed,Pane layer, String path) {
        super( hP, speed,layer);
        loadImage(path);
    }

    //method loading image to imageView
    private void loadImage(String path) {
        ImageView ii;
        ii = new ImageView(path);
        this.image = ii.getImage();
        this.width = this.image.getWidth();
        //starting width
        setx(320 - getWidth()/2);
        this.height = this.image.getHeight();
        this.imageView = ii;
        this.imageView.relocate(this.getx(), this.gety());
        this.addToLayer();
    }

    public double gety() {
        return y;
    }

    public double getx(){
        return x;
    }

    public double getDx() {
        return dx;
    }

    public void sety(double y) {
        this.y = y;
    }

    public void setx(double x){
        this.x = x;
    }

    //moving spaceShip to the right
    public void moveXRight() {   //Metoda odpowiadająca za poruszanie się
        this.setx(this.getx() + this.getDx());
    }

    //moving spaceShip to the left
    public void moveXLeft() {   //Metoda odpowiadająca za poruszanie się
        this.setx(this.getx() - this.getDx());
    }

    //method relocate in here
    public void relocation(){
        this.imageView.relocate(getx(),gety());
    }

}
