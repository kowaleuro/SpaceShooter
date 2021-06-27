package spacegierka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EntryPaneController {

    public static String nickname;
    public static ArrayList<String> highScores;

    @FXML
    private TextField nickField;

    @FXML
    private Button submitButton;

    @FXML
    private Button highScoreButton;

    public void initialize() {
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nickField.getText() != null) {
                    setNickname(nickField.getText());
                }
                setHighScores();
                Main main = new Main();
                Stage stage = (Stage) submitButton.getScene().getWindow();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setHighScores();
                Stage stage = (Stage) highScoreButton.getScene().getWindow();
                try {
                    AnchorPane hPane = FXMLLoader.load(getClass().getResource("/fxml/highScorePane.fxml"));
                    int y = 60;
                    for (String s: highScores) {
                        Label zmienna = new Label(s);
                        zmienna.setLayoutX(240);
                        y = y + 30;
                        zmienna.setLayoutY(y);
                        hPane.getChildren().add(zmienna);

                    }
                    Scene scene = new Scene(hPane);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ArrayList<String> getHighestScores(){
        Scanner s = null;
        try {
            s = new Scanner(new File("src\\main\\resources\\textFile\\HighScore.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    public void setHighScores(){
        highScores = getHighestScores();
    }


}
