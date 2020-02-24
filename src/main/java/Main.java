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
        // Cargo el fxml
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        // Le pongo un titulo al stage que se abre
        stage.setTitle("MP3-UF5-A4 Aplicació Cinema amb JavaFX");
        // Le pongo un tamaño a mi escena
        stage.setScene(new Scene(root, 1400, 800));
        // Muestro el stage
        stage.show();
    }
}
