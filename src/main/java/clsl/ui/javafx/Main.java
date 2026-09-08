package clsl.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Starts the JavaFX graphical user interface. */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent mainLayout = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        Scene scene = new Scene(mainLayout, 400, 600);
        stage.setTitle("Clsl");
        stage.setScene(scene);
        stage.show();
    }
}
