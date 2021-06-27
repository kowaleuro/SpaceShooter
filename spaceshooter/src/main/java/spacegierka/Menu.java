package spacegierka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane entryPane = FXMLLoader.load(getClass().getResource("/fxml/entryPane.fxml"));
        Scene scene = new Scene(entryPane);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/asteroid.png")));
        stage.setScene(scene);
        stage.setTitle("Spaceshooter");
        stage.show();
    }
}
