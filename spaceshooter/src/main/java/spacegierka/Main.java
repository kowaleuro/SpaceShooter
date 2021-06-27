package spacegierka;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import static spacegierka.EntryPaneController.highScores;
import static spacegierka.EntryPaneController.nickname;

public class Main extends Application {

    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private ArrayList<Integer> asteroidsToDestroy = new ArrayList<>();
    private ArrayList<Integer> bulletsToDestroy = new ArrayList<>();
    private ArrayList<Heart> hearts = new ArrayList<>();
    private Score scoreLabel;
    private AmmoAmount ammoAmount;
    private Board board;
    private Pane layer;
    private Spaceship spaceship;
    private static int score = 0;
    private boolean gameOn = true;
    private int wait = 10;
    private ArrayList<String> errors = new ArrayList<>();
    private LocalTime currentTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Board board = new Board(mainPane);
        this.layer = mainPane;
        board.setSpaceship();
        this.spaceship = board.spaceship;
        this.board = board;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        stage.setScene(this.board);
        stage.setTitle("Spaceshooter");
        stage.show();
        heartSet();
        this.board.moveOnBoard();
        Score score = new Score(layer);
        this.scoreLabel = score;
        this.ammoAmount = new AmmoAmount(layer);
        if (gameOn == true) {
            timeline.play();
        }
        if (gameOn == false) {
            timeline.stop();
            this.board.gameOn = false;
        }
    }

    public void run(){
        if(gameOn) {

            createEnemy(wait);
            detectShip();
            detectBullet();
            deleteBullet();
            deleteEnemy();
            board.bullets.forEach(Bullet::moveUp);
            asteroids.forEach(Asteroid::fall);
            endGame();
            updateScore();
            ammo();
            destroyObejcts();
            currentTime = LocalTime.now();
        }else {
            typeScore(score);
            loggerIn();
            new GameOver(1,1,layer,64,100);
        }

    }

    public void createEnemy(int earlier){
        Random rand = new Random();
        int x = rand.nextInt(5);
        if(x == 0 && x != earlier){
            asteroids.add(new Asteroid(1,1,layer));
            this.wait = x;
        }
        else this.wait = 5;
    }

    //removing object out of ArrayList and increasing score
    public void deleteEnemy(){
        for (Asteroid x:asteroids) {
            if(x.ifOutOfBounds() && score > 0){
                score--;
                asteroidsToDestroy.add(asteroids.indexOf(x));
            }
            else if(x.ifOutOfBounds() && score <= 0)
            {
                asteroidsToDestroy.add(asteroids.indexOf(x));
            }
        }
    }

    //collision detection
    public void detectShip(){
        for (Asteroid x:asteroids) {
            if(x.collision(this.spaceship)){
                hpCalc();
                asteroidsToDestroy.add(asteroids.indexOf(x));
            };
        }
    }

    public void detectBullet(){
        for (Asteroid x:asteroids) {
            for (Bullet y:board.bullets) {
                if(x.collision(y)){
                    asteroidsToDestroy.add(asteroids.indexOf(x));
                    bulletsToDestroy.add(board.bullets.indexOf(y));
                    increaseScore();
                };
            }
        }

    }

    public void deleteBullet(){
        for (Bullet x:board.bullets) {
            if(x.ifOutOfBounds()){
                bulletsToDestroy.add(board.bullets.indexOf(x));
            }
        }
    }

    public void heartSet(){
        for(int i = 0; i < 3; i++){
            hearts.add(new Heart(1,1,this.layer,600 - i*30,50));
        }
    }

    public void endGame(){
        if(spaceship.getHealthPoints() <= 0 ){
           gameOn = false;
        }
    }

    public void hpCalc(){
        if (spaceship.getHealthPoints() > 0) {
            spaceship.setHealthPoints(spaceship.getHealthPoints() - 1);
            hearts.get(spaceship.getHealthPoints()).removeFromLayer();
            hearts.remove(spaceship.getHealthPoints());
        }
    }

    public void increaseScore(){
        this.score = score + 2;
    }

    public void updateScore(){
        scoreLabel.increase(score);
    }

    public void ammo(){
        this.ammoAmount.change(board.bullets.size());
    }

    public void typeScore(int score){
        ArrayList<String> ama = new ArrayList<>();
        int max = 0;
        String zmienna = null;
        for (String x : highScores) {


            if(max > 0) {
                ama.add(zmienna);
                zmienna = x;
            } else if (Integer.parseInt(x.split(":")[1]) < score){
                max = score;
                ama.add(nickname + ":" + score);
                zmienna = x;
            }else {
                ama.add(x);
            }

        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\main\\resources\\textFile\\HighScore.txt");
            for(String str: ama) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loggerIn(){
        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\main\\resources\\textFile\\logger.txt");
            for(String str: errors) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroyObejcts(){
        for (int x: asteroidsToDestroy) {
            try {
                asteroids.get(x).removeFromLayer();
                asteroids.remove(asteroids.get(x));
            }catch (Exception e) {
                errors.add(e + " " + currentTime);
            }
        }
        asteroidsToDestroy.clear();

        for (int x: bulletsToDestroy) {
            try {
                board.bullets.get(x).removeFromLayer();
                board.bullets.remove(board.bullets.get(x));
            }catch (Exception e) {
                errors.add(e + " " + currentTime);
            }
        }
        bulletsToDestroy.clear();

    }


}
