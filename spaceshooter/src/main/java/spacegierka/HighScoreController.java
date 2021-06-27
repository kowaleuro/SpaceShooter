package spacegierka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HighScoreController {

    @FXML
    private Button goBackButton;

    public void initialize() {
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) goBackButton.getScene().getWindow();
                try {
                    AnchorPane entryPane = FXMLLoader.load(getClass().getResource("/fxml/entryPane.fxml"));
                    Scene scene = new Scene(entryPane);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}

