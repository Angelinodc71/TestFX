import control.Film;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        stage.setTitle("HOLA GUAPO");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }
}
