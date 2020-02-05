package control;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    int i = 0;
    ObservableList<String> observableList = FXCollections.observableArrayList();
    @FXML
    ListView<String> listView;
    @FXML
    Button btn001;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            metodo();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        i=0;

    }

    public void onClick(MouseEvent mouseEvent) throws IOException, JAXBException {
//        for (int j = 0; j < films.size() ; j++) {
//
//        }
        btn001.setText(String.valueOf(films.get(i++)));

    }

    private List<Film> films;
    void metodo () throws IOException, JAXBException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.films = ((Films)jaxbUnmarshaller.unmarshal(is)).films;
        listView.setItems(observableList);
        observableList.addAll(String.valueOf(films));

    }
}
