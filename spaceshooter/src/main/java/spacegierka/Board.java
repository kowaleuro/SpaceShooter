package spacegierka;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class Board extends Scene {

    public Spaceship spaceship;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public Pane layer;
    public boolean gameOn = true;

    //Constructor
    public Board(Pane parent) {
        super(parent);
        this.layer = parent;
    }

    //keyListener, moving ship and also shooting
    public void moveOnBoard(){
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.RIGHT && 640 - spaceship.getWidth() - spaceship.getDx() > spaceship.getx()){
                    spaceship.moveXRight();
                    spaceship.relocation();
                } else if (event.getCode() == KeyCode.LEFT && spaceship.getDx() < spaceship.getx()) {
                    spaceship.moveXLeft();
                    spaceship.relocation();
//                } else if (event.getCode() == KeyCode.SPACE) {
//                    createBullet();
                }

            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    createBullet();
                }
            }
        });
    }


    //Creating new bullet and adding it to the ArrayList
    public void createBullet(){
        if (bullets.size() < 10) {
            bullets.add(new Bullet(10, 15, layer, "file:src\\main\\resources\\image\\ammo_green.png", spaceship));
        }
    }

    //method setting spaceship
    public void setSpaceship(){
        Spaceship spaceship = new Spaceship(3,1, this.layer,"file:src\\main\\resources\\image\\ship.png");
        this.spaceship = spaceship;
    }
}
