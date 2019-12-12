package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Mandlebrot extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        javafx.event.EventTarget a;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/jfx/ui.fxml"));

            Scene scene = new Scene(root, 1224, 1024);

            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
