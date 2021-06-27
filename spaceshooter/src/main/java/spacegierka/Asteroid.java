package spacegierka;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Asteroid extends Component{

    private double x;
    private double y = 0;
    private double dy = 18;

    //Constructor
    Asteroid(int hP, double speed, Pane layer) {
        super(hP, speed, layer);
        spawnPoint();
        loadImage();
    }

    // loading image and getting it's width and height
    public void loadImage() {
        ImageView ii;
        ii = new ImageView("file:src\\main\\resources\\image\\asteroid.png");
        this.image = ii.getImage();
        this.width = this.image.getWidth();
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

    public void sety(double y) {
        this.y = y;
    }

    public void setx(double x){
        this.x = x;
    }

    //method deciding where enemy can spawn
    public void spawnPoint() {
        Random rand = new Random();
        int x = rand.nextInt(10);
        setx(x * 64);
    }

    //method changing position of an asteroid and relocating it's image
    public void fall(){
        this.sety(this.gety() + dy);
        imageView.relocate(x, y);
    }

    //checking if asteroid is out of bounds
    public boolean ifOutOfBounds(){
        if (this.gety() >= 800){
            return true;
        }
        else return false;
    }

    //calculating collision with spaceship
    public boolean collision(Spaceship spaceship){
        Rectangle rect1 = new Rectangle(x,y,width,height);
        Rectangle rect2 = new Rectangle(spaceship.getx(),spaceship.gety(),spaceship.getWidth(),spaceship.getHeight());
        if (rect1.getX() < rect2.getX() + rect2.getWidth() &&
                rect1.getX() + rect1.getWidth() > rect2.getX() &&
                rect1.getY() < rect2.getY() + rect2.getHeight() &&
                rect1.getY() + rect1.getHeight() > rect2.getY()) {
            return true;
        }
        else return false;
    }

}
