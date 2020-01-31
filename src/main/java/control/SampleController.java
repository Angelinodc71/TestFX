package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    int i = 0;
    @FXML
    Button btn001;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        i=0;
    }

    public void onClick(MouseEvent mouseEvent){
        btn001.setText(" "+i++);
    }
}
