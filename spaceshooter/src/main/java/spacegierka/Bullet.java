package spacegierka;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends Spaceship{

    private Spaceship spaceship;


    //Constructor
    public Bullet(int hP, double speed, Pane layer, String path, Spaceship spaceship) {
        super(hP, speed, layer, path);
        this.spaceship = spaceship;
        spawnBullet();
    }

    //calculating bullet's spawn point
    public void spawnBullet(){
        this.setx(spaceship.getx()+spaceship.getWidth()/2);
        this.sety(spaceship.gety());
        relocation();
    }

    //moveUp
    public void moveUp(){
        this.sety(this.gety() - getSpeed());
        imageView.relocate(getx(), gety());
    }

    //check if out of bounds
    public boolean ifOutOfBounds(){
        if (this.gety() <= 0){
            return true;
        }
        else return false;
    }

}
